import { replace } from 'connected-react-router'
import { toast } from 'react-toastify';
import { change } from 'redux-form';

import * as _ from 'lodash'
import { api } from '../../../shared/index';
import moment from 'moment';

export const LOAD_ELEMENT = "PRODUCT/CREATE/LOAD_CREATE_ELEMENT"
export const LOADED_ELEMENT = "PRODUCT/CREATE/LOADED_CREATE_ELEMENT"

export const REQUEST_CREATE_ELEMENT = "PRODUCT/CREATE/REQUEST_PRODUCT"
export const RESPONSE_CREATE_PRODUCT = "PRODUCT/CREATE/RESPONSE_PRODUCT"
export const ERROR_CREATE_PRODUCT = "PRODUCT/CREATE/ERROR_PRODUCT"

const LOADED_RELATED = "PRODUCT/CREATE/LOADED_RELATED"
const LOAD_RELATED = "PRODUCT/CREATE/LOAD_RELATED"

let initialState = {
    element: {
        sku: '',
        upc: '',
        name: '',
        brand: '',
        minStock: 100,
        stock: '',
        providerPrice: '',
        retailPrice: '',
        description: '',
        batchDetails: '',
        expiration: moment().toDate(),
        type: 'DEFAULT',
        providerId: "",
        categories:[]
    },
    providers: [],
    categories: [],
    loading: false,
    error: null
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {

        case REQUEST_CREATE_ELEMENT:
            return { ...state, loading: true }
        case RESPONSE_CREATE_PRODUCT:
            return { ...state, loading: false }
        case LOADED_RELATED:
            //TODO: VER SI FUNCIONA CON CATEGORIAS LUEGO
            return { ...state, loading: false, ...action.payload }
        case ERROR_CREATE_PRODUCT:
            return { ...state, loading: false, error: action.error }
        default:
            return state;
    }
}

export const create = (element) => (dispatch) => {
    dispatch({ type: REQUEST_CREATE_ELEMENT })

    api.post("/product", element)
        .then((response) => {
            dispatch({ type: RESPONSE_CREATE_PRODUCT, payload: response.data })
            let location = { pathname: "/product", created: element.id }
            dispatch(replace(location));
            toast.success("Producto Creado")
        })
        .catch((error) => {
            dispatch({ type: ERROR_CREATE_PRODUCT, error: error })
            toast.error("Error al crear Producto")
        })
}

export const load = (id) => (dispatch, state) => {
    //dispatch()
    api.get("/provider/all").then((result) => {
        dispatch({ type: LOADED_RELATED, payload: { providers: result.data } })
    })
    
    api.get("/product/category/all").then((result) => {
        dispatch({ type: LOADED_RELATED, payload: { categories: result.data } })
    })
}

export const goBack = () => dispatch => {
    dispatch(replace('/product'));
    toast.info("Edición cancelada")
}