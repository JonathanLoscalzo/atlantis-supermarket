import { replace, goBack as gb, go, push } from 'connected-react-router'
import { toast } from 'react-toastify';
import _ from 'lodash'
import { api } from '../../../../shared'
import { removeElementAt } from '../list';

export const REQUEST_REMOVE = "CATEGORY/REMOVE/REQUEST"
export const RESPONSE_REMOVE = "CATEGORY/REMOVE/RESPONSE"
export const ERROR_REMOVE = "CATEGORY/REMOVE/ERROR"

export const LOAD_REMOVE = "CATEGORY/REMOVE/LOAD_REMOVE"
export const LOADED_REMOVE = "CATEGORY/REMOVE/LOADED_REMOVE"
export const LOADED_ERROR = "CATEGORY/REMOVE/LOADED_ERROR"

let initialState = {
    element: null,
    error: null,
    isOpen: false
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {

        case LOAD_REMOVE:
            return { ...initialState }
        case LOADED_REMOVE:
            return { ...state, element: action.payload, isOpen: true }
        case LOADED_ERROR:
            return { ...state, element: null, error: action.error }

        case REQUEST_REMOVE:
            return { ...state, }
        case RESPONSE_REMOVE:
            return { ...state, element: null }
        case ERROR_REMOVE:
            return { ...state, error: action.error }

        default:
            return state;
    }
}

export const load = (id) => (dispatch, state) => {
    dispatch({ type: LOAD_REMOVE })

    api.get(`/product/category/${id}`)
        .then(res => {
            let element = res.data
            if (element) {
                dispatch({ type: LOADED_REMOVE, payload: element })
            } else {
                dispatch(replace('/category'));
                toast.warn("No se puede eliminar la categoria seleccionada")
            }
        })
}

export const remove = (id) => (dispatch, state) => {
    dispatch({ type: REQUEST_REMOVE })

    let element = state().category.remove.element;

    api.delete(`/product/category/${element.id}`)
        .then(response => {
            toast.success("Elemento eliminado")
            dispatch({ type: RESPONSE_REMOVE, payload: element })
            let location = { pathname: "/category", removed: element.id }
            dispatch(replace(location))
        }).catch(() => {
            toast.error("Error al eliminar categorias")
        })

}

export const goBack = () => dispatch => {
    // TODO: mensaje cancelada
    //dispatch({ type: LOAD_REMOVE })
    dispatch(gb());
}