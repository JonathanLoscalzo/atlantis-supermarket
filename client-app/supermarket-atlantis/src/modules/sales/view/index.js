
import { goBack as gb, replace } from 'connected-react-router'
import { api } from 'modules/shared';
import { getSale } from 'modules/sales/list/index';
const FETCH = "SALES/FETCH_ELEMENT"
const FETCHED = "SALES/FETCHED_ELEMENT"

const initialState = {
    element: null,
    loading: true,
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case FETCH:
            return { ...state, loading: true };
        case FETCHED:
            return { ...state, element: action.payload, loading: false };
        default:
            return state;
    }
}

export const getElement = (id) => (dispatch, getState) => {
    let sale = getSale(getState(), id)
    if (!sale){
        return dispatch(replace("/sales"));
    }
    return dispatch({ type: FETCHED, payload: sale })
}

export const goBack = () => (dispatch) => {
    dispatch(gb())
}