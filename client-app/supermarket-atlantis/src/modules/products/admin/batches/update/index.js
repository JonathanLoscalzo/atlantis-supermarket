import { replace } from 'connected-react-router'
import { toast } from 'react-toastify';
import { change } from 'redux-form';
import moment from 'moment';

import * as _ from 'lodash'
import { api } from '../../../../shared'

export const LOAD_ELEMENT = "PRODUCT/UPDATE/LOAD_CREATE_ELEMENT"
export const LOADED_ELEMENT = "PRODUCT/UPDATE/LOADED_CREATE_ELEMENT"

export const REQUEST_UPDATE_ELEMENT = "PRODUCT/UPDATE/REQUEST_PROVIDER"
export const RESPONSE_UPDATE = "PRODUCT/UPDATE/RESPONSE"
export const ERROR_UPDATE = "PRODUCT/UPDATE/ERROR"

const LOADED_RELATED = "PRODUCT/UPDATE/LOADED_RELATED"
const LOAD_RELATED = "PRODUCT/UPDATE/LOAD_RELATED"

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
        provider: null,
        categories: []
    },
    providers: [],
    categories: [],
    loading: false,
    error: null
}

export default function reducer(state = initialState, action = {}) {
    let diff;

    switch (action.type) {
        case LOAD_ELEMENT:
            return { ...state, loading: true }
        case LOADED_ELEMENT:
            return { ...state, loading: false, element: action.payload }
        case REQUEST_UPDATE_ELEMENT:
            return { ...state, loading: true }
        case RESPONSE_UPDATE:
            return { ...state, loading: false }
        case ERROR_UPDATE:
            return { ...state, loading: false, error: action.error }
        case LOADED_RELATED:
            return { ...state, loading: false, ...action.payload }
        default:
            return state;
    }
}

export const update = (element) => (dispatch, getState) => {
    dispatch({ type: REQUEST_UPDATE_ELEMENT })

    api.put("/product", element)
        .then((response) => {
            dispatch({ type: RESPONSE_UPDATE, payload: response.data })

            let location = { pathname: "/product", updated: element.id }
            dispatch(replace(location));
            toast.success("producto Modificado")
        })
        .catch((error) => {
            dispatch({ type: ERROR_UPDATE, error: error })
            toast.error("Error al modificar producto")
        })
}

export const load = (id) => (dispatch, state) => {
    dispatch({ type: LOAD_ELEMENT })
    api.get(`/product/${id}`)
        .then(res => {
            let order = res.data
            order.categories = order.categories.map(c => c.id);
            order.providerId = order.provider != null ? order.provider.id : null;
            if (order) {
                dispatch({ type: LOADED_ELEMENT, payload: order })

                api.get("/provider/all").then((result) => {
                    dispatch({ type: LOADED_RELATED, payload: { providers: result.data } })
                })

                api.get("/product/category/all").then((result) => {
                    dispatch({ type: LOADED_RELATED, payload: { categories: result.data } })
                })
            } else {
                dispatch(replace('/product'));
                toast.warn("No se puede editar el product seleccionado")
            }
        })

}

export const goBack = () => dispatch => {
    dispatch(replace('/product'));
    toast.info("Edición cancelada")
}