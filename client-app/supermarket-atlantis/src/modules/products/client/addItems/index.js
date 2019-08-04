import { goBack as gb } from 'connected-react-router'
import handler from 'modules/shared/handler';
import { addItemToBasket } from 'modules/cart/list/index'
import { toast } from 'react-toastify';

const SET_PRODUCT = 'PRODUCT/ADD_ITEMS/SET'

let initialState = {
    element: {
        product: null,
        quantity: 1,
    },
    error: null,
    isOpen: false,
}

const handleSetProduct = (state, action) => {
    return {
        ...state,
        isOpen: true,
        element: {
            quantity: 1,
            product: action.payload.product
        }
    }
}

const handlers = {
    [SET_PRODUCT]: handleSetProduct
}

export default function handle(state = initialState, action = {}) {
    return handler(handlers, state, action);
}

export const goBack = () => dispatch => {
    dispatch(gb());
}

export const submit = (form) => (dispatch) => {
    dispatch(addItemToBasket(form.product, parseInt(form.quantity)));
    toast.success("Agregado al carrito!")
    dispatch(gb());
}

export const setProduct = (product) => {
    return {
        type: SET_PRODUCT,
        payload: {
            product: product,
            quantity: 1
        }
    }
}