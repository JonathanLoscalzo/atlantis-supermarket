
import { goBack as gb } from 'connected-react-router'
import { api } from '../../../shared'

const FETCH = "PRODUCT/ADMIN/FETCH_ELEMENT"
const FETCHED = "PRODUCT/ADMIN/FETCHED_ELEMENT"

const initialState = {
    element: null,
    loading: true,
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case FETCH:
            return { ...state, loading:true };
        case FETCHED:
            return { ...state, element: action.payload, loading: false };
        default:
            return state;
    }
}

export const getElement = (id) => (dispatch, getState) => {
    dispatch({ type: FETCH })
    api.get(`/product/${id}`).then(result => {
        dispatch({ type: FETCHED, payload: result.data })
    }).catch(() => {

    })
}

export const goBack = () => (dispatch) => {
    dispatch(gb())
}