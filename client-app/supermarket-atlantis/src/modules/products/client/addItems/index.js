import { goBack as gb } from 'connected-react-router'
import handler from 'modules/shared/handler';
import { addItemToBasket, getItemSelector } from 'modules/cart/list/index'
import { toast } from 'react-toastify';
import ProductList from 'modules/products/admin/batches/list/presentational/ProductList';

const SET_PRODUCT = 'PRODUCT/ADD_ITEMS/SET'

let initialState = {
    element: {
        exists: null,
        product: null,
        quantity: 1,
        maxUnits: 0,
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
            product: action.payload.product,
            exists: action.payload.exists,
            maxUnits: action.payload.product.units - (action.payload.exists ? action.payload.exists.quantity : 0)
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

export const setProduct = (product) => (dispatch, getState) => {
    
    

    dispatch({
        type: SET_PRODUCT,
        payload: {
            exists: getItemSelector(getState(), product.id) || null,
            product: product,
            quantity: 1
        }
    });
}