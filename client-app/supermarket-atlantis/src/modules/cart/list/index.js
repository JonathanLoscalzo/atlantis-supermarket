// @ts-ignore
import _ from 'lodash'
import { api } from 'modules/shared'
import { toast } from 'react-toastify';
const ADD_BASKET = "BASKET/ADD";
const DELETE_ITEM_BASKET = "BASKET/DELETE";
const GET_PAYMENT_METHODS = "BASKET/GET_PAYMENT_METHOD"
const CHANGE_SELECTABLE = "BASKET/CHANGE_SELECTABLE"
const CHANGE_PAY = "BASKET/CHANGE_PAY"

const initialState = {
    loading: false,
    payments: [],
    element: {
        payment: '',
        pay: 0,
        items: []
    },
    payment: null,
    total: 0,
    change: null,
}

const handleAddBasket = (state, action) => {
    let elem = _.find(state.element.items, (f) => f.product.id === action.payload.product.id)
    let data;
    if (elem) {
        elem.quantity += action.payload.quantity
        data = {
            ...state,
            element: {
                items: [
                    ..._.filter(state.element.items, (f) => f.product.id !== action.payload.product.id)
                    , elem
                ]
            }
        }
    } else {
        data = {
            ...state,
            element: {
                ...state.element,
                items: [...state.element.items, action.payload]
            }
        }
    }

    return {
        ...data,
        total: getTotal(data)
    }
}

const handleDeleteItem = (state, action) => {
    let data = {
        ...state,
        element:
        {
            ...state.element,
            items: [
                ..._.filter(state.element.items, (f) => f.product.id !== action.payload.product.id)
            ]
        }
    }

    return {
        ...data,
        total: getTotal(data)
    }
}

const handleGetPaymenetMethods = (state, action) => {
    return {
        ...state,
        payments: action.payload
    }
}

const handleChangeSelectable = (state, action) => {
    return {
        ...state,
        payment: state.payments.find(x => x.id == action.payload) || ''
    }
}

const handleChangePay = (state, action) => {
    let payment = state.payment;
    let totalCost = null;
    if (payment) {
        if (payment.allowChange) {
            totalCost = `Su cambio $ ${(state.total || 0) - action.payload}`
        } else {
            if (action.payload == state.total) {
                totalCost = "Pago Exacto!"
            } else {
                totalCost = "La forma de pago no permite dar cambio! (Pago Exacto)"
            }
        }
    }
    return {
        ...state,
        change: totalCost
    }
}

const handlers = {
    [ADD_BASKET]: handleAddBasket,
    [DELETE_ITEM_BASKET]: handleDeleteItem,
    [GET_PAYMENT_METHODS]: handleGetPaymenetMethods,
    [CHANGE_SELECTABLE]: handleChangeSelectable,
    [CHANGE_PAY]: handleChangePay
}

export default function handler(state = initialState, action = {}) {
    const handler = handlers[action.type];
    return handler ? handler(state, action) : state;
}

export function addItemToBasket(product, quantity) {
    return {
        type: ADD_BASKET,
        payload: {
            product: product,
            quantity: quantity
        }
    }
}

export function deleteItem(product) {
    return {
        type: DELETE_ITEM_BASKET,
        payload: {
            product
        }
    }
}

const getPaymentAction = (items) => ({
    type: GET_PAYMENT_METHODS,
    payload: items
})

export const getPaymentMethods = () => (dispatch) => {
    api.get('/sale/paymentMethods')
        .then(res => {
            dispatch(getPaymentAction(res.data))
        })
        .catch((err) => toast.error("Ocurrió un error obteniendo los tipos de pago"))
}

export const changeSelectable = (id) => {
    return {
        type: CHANGE_SELECTABLE,
        payload: id
    }
}

export const changePay = (value) => {
    return {
        type: CHANGE_PAY,
        payload: parseInt(value)
    }
}


export const submit = (values) => (dispatch, getState) => {

    if (values.items.length == 0) {
        toast.error("Tiene que haber al menos un producto en el carrito!")
        return;
    }

    let total = getState().cart.list.total;

    //validar si total < pay y no se permite dar cambio.
    let payment = getState().cart.list.payment

    if (payment && !payment.allowchange && total < values.pay) {
        toast.error("la forma de pago no acepta dar vuelto! completar exacto!")
    }

    if (total > values.pay) {
        toast.error("No alcanza para pagar!")
    }

    console.log(values)
}

export function getItemSelector(state, id) {
    return state.cart.list.element.items.find(x => x.product.id === id)
}

function getTotal(cart) {
    try {
        return cart.element.items.map(x => ({ quantity: x.quantity, price: x.product.retailPrice }))
            .reduce((a, b) => a + b.quantity * b.price, 0);
    } catch (error) {
        return 0;
    }
}





