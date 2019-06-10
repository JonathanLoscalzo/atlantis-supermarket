import { replace, goBack as gb } from 'connected-react-router'
import { toast } from 'react-toastify';
import { change } from 'redux-form';
import moment from 'moment';

import * as _ from 'lodash'
import { api } from '../../../../shared'

export const LOAD_ELEMENT = "BATCH/UPDATE/LOAD_CREATE_ELEMENT"
export const LOADED_ELEMENT = "BATCH/UPDATE/LOADED_CREATE_ELEMENT"

export const REQUEST_UPDATE_ELEMENT = "BATCH/UPDATE/REQUEST_BATCH"
export const RESPONSE_UPDATE = "BATCH/UPDATE/RESPONSE"
export const ERROR_UPDATE = "BATCH/UPDATE/ERROR"

const LOADED_RELATED = "BATCH/UPDATE/LOADED_RELATED"
const LOAD_RELATED = "BATCH/UPDATE/LOAD_RELATED"

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
    api.put("/product/batch/edit", element)
        .then((response) => {
            dispatch({ type: RESPONSE_UPDATE, payload: response.data })
            let location = { pathname: "/batch", created: element.id }
            dispatch(replace(location));
            toast.success("Lote Modificado")
        })
        .catch((error) => {
            toast.error("Error al persistir Lote intente nuevamente")
            // dispatch(gb());
        })
}

export const load = (id) => (dispatch, state) => {
    dispatch({ type: LOAD_RELATED })
    api.get(`/product/batch/${id}`)
        .then((result) => {
            dispatch({
                type: LOADED_RELATED,
                payload: {
                    element: {
                        product: {
                            id: result.data.product.id
                        },
                        type: result.data.product.type,
                        id: result.data.id,
                        detail: result.data.detail,
                        expiration: moment(result.data.expiration),
                        remainingUnits: result.data.remainingUnits,
                    }
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