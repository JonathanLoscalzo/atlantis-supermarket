import { replace } from 'connected-react-router'
import { toast } from 'react-toastify';
import { change } from 'redux-form';

import * as _ from 'lodash'
import { api } from '../../../../shared'

export const LOAD_ELEMENT = "CATEGORY/UPDATE/LOAD_CREATE_ELEMENT"
export const LOADED_ELEMENT = "CATEGORY/UPDATE/LOADED_CREATE_ELEMENT"

export const REQUEST_UPDATE_ELEMENT = "CATEGORY/UPDATE/REQUEST_PROVIDER"
export const RESPONSE_UPDATE = "CATEGORY/UPDATE/RESPONSE_PROVIDER"
export const ERROR_UPDATE = "CATEGORY/UPDATE/ERROR_PROVIDER"

let initialState = {
    name: "",
    phone: "",
    email: "",

    loading: true,
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
        default:
            return state;
    }
}

export const update = (element) => (dispatch) => {
    dispatch({ type: REQUEST_UPDATE_ELEMENT })

    api.put("/product/category", element)
        .then((response) => {
            dispatch({ type: RESPONSE_UPDATE, payload: response.data })

            let location = { pathname: "/category", updated: element.id }
            dispatch(replace(location));
            toast.success("categoria Modificado")
        })
        .catch((error) => {
            dispatch({ type: ERROR_UPDATE, error: error })
            toast.error("Error al modificar categoria")
        })
}

export const load = (id) => (dispatch, state) => {
    dispatch({ type: LOAD_ELEMENT })
    api.get(`/product/category/${id}`)
        .then(res => {
            let order = res.data
            if (order) {
                dispatch({ type: LOADED_ELEMENT, payload: order })
            } else {
                dispatch(replace('/category'));
                toast.warn("No se puede editar el proveedor seleccionado")
            }
        })

}

export const goBack = () => dispatch => {
    dispatch(replace('/category'));
    toast.info("Edición cancelada")
}