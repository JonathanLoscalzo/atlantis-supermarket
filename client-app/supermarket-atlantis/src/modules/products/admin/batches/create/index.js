import { replace, goBack as gb } from 'connected-react-router'
import { toast } from 'react-toastify';
import { change } from 'redux-form';

import * as _ from 'lodash'
import { api } from '../../../../shared/index';
import moment from 'moment';

export const LOAD_ELEMENT = "BATCH/CREATE/LOAD_CREATE_ELEMENT"
export const LOADED_ELEMENT = "BATCH/CREATE/LOADED_CREATE_ELEMENT"

export const REQUEST_CREATE_ELEMENT = "BATCH/CREATE/REQUEST_BATCH"
export const RESPONSE_CREATE_BATCH = "BATCH/CREATE/RESPONSE_BATCH"
export const ERROR_CREATE_BATCH = "BATCH/CREATE/ERROR_BATCH"

const LOADED_RELATED = "BATCH/CREATE/LOADED_RELATED"
const LOAD_RELATED = "BATCH/CREATE/LOAD_RELATED"

let initialState = {
    element: {
        id: '',
        productId: '',
        detail: '',
        expiration: '',
        remainingUnits: '',
        type: 'DEFAULT' //FOR VALIDATION
    },
    loading: false,
    error: null
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {

        case REQUEST_CREATE_ELEMENT:
            return { ...state, loading: true }
        case RESPONSE_CREATE_BATCH:
            return { ...state, loading: false }
        case LOADED_RELATED:
            return { ...state, loading: false, ...action.payload }
        case ERROR_CREATE_BATCH:
            return { ...state, loading: false, error: action.error }
        default:
            return state;
    }
}

export const create = (element) => (dispatch) => {
    dispatch({ type: REQUEST_CREATE_ELEMENT })
    api.post("/product/batch", element)
        .then((response) => {
            dispatch({ type: RESPONSE_CREATE_BATCH, payload: response.data })
            let location = { pathname: "/batch", created: element.id }
            dispatch(replace(location));
            toast.success("Producto Creado")
        })
        .catch((error) => {
            toast.error("Error al persistir Lote intente nuevamente")
            // dispatch(gb());
        })
}

export const load = (id) => (dispatch, state) => {
    //dispatch()
    dispatch({ type: LOAD_RELATED })
    api.get(`/product/${id}`)
        .then((result) => {
            dispatch({
                type: LOADED_RELATED,
                payload: {
                    element: { productId: id, type: result.data.type }
                }
            })
        })
        .catch((error) => {
            toast.error("Error al buscar Producto, intente nuevamente")
            dispatch(gb());
        })
}

export const goBack = () => dispatch => {
    dispatch(replace('/batch'));
    toast.info("Edición cancelada")
}