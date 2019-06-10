import { replace, goBack as gb, go, push } from 'connected-react-router'
import { toast } from 'react-toastify';
import _ from 'lodash'
import { formValueSelector } from 'redux-form'

import { api } from '../../../../shared'

export const REQUEST_SUPPLY = "BATCH/SUPPLY/REQUEST"
export const RESPONSE_SUPPLY = "BATCH/SUPPLY/RESPONSE"
export const ERROR_SUPPLY = "BATCH/SUPPLY/ERROR"

export const LOAD_SUPPLY = "BATCH/SUPPLY/LOAD_SUPPLY"
export const LOADED_SUPPLY = "BATCH/SUPPLY/LOADED_SUPPLY"
export const LOADED_ERROR = "BATCH/SUPPLY/LOADED_ERROR"

let initialState = {
    element: {
        id: null,
        quantity: 1,
    },
    error: null,
    isOpen: false,
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {

        case LOAD_SUPPLY:
            return { ...initialState }
        case LOADED_SUPPLY:
            return { ...state, element: { ...state.element, id: action.payload }, isOpen: true }
        case LOADED_ERROR:
            return { ...state, element: null, error: action.error }

        case REQUEST_SUPPLY:
            return { ...state, }
        case RESPONSE_SUPPLY:
            return { ...state, element: null }
        case ERROR_SUPPLY:
            return { ...state, error: action.error }

        default:
            return state;
    }
}

export const load = (id) => (dispatch, state) => {
    dispatch({ type: LOAD_SUPPLY })

    api.get(`/product/batch/${id}`)
        .then(res => {
            let element = res.data
            if (element) {
                dispatch({ type: LOADED_SUPPLY, payload: id })
            } else {
                dispatch(gb());
                toast.warn("No se puede abastecer el lote seleccionado")
            }
        }).catch(() => {
            dispatch(gb());
            toast.warn("No se puede abastecer el lote seleccionado")
        })
}

export const supply = (id) => (dispatch, state) => {
    dispatch({ type: REQUEST_SUPPLY })

    let selector = formValueSelector("batch/supply");
    let element = selector(state(),"id","quantity")

    api.put(`/product/batch/${element.id}`, element)
        .then(response => {
            toast.success("Lote abastecido")
            dispatch({ type: RESPONSE_SUPPLY, payload: element })
            let location = { pathname: "/batch", supplied: element.id }

            dispatch(replace(location))
        }).catch(() => {
            toast.error("Error al abastecer lote")
        })

}

export const goBack = () => dispatch => {
    dispatch({ type: LOAD_SUPPLY })
    dispatch(gb());
}