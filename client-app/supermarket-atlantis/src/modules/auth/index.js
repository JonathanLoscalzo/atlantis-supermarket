import api from '../../common/api';
import { replace, push, go } from 'connected-react-router';
import { toast } from 'react-toastify';

const AUTH_REQUEST = 'AUTH/REQUEST';
const AUTH_RESPONSE = 'AUTH/RESPONSE';
const AUTH_ERROR = 'AUTH/ERROR';
const AUTH_CANCEL = 'AUTH/CANCEL';
const AUTH_LOGOUT = 'AUTH/LOGOUT';
const AUTH_SIGNUP = 'AUTH/SIGNUP';

const initialState = {
    loading: false,
    authenticated: false,
    errorMessage: null,
    isSignup: false,
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case AUTH_REQUEST:
            return { ...state, loading: true };
        case AUTH_RESPONSE:
            return { ...state, loading: false, authenticated: true };
        case AUTH_ERROR:
            return {
                ...state,
                loading: false,
                authenticated: false,
                errorMessage: action.error
            };
        case AUTH_CANCEL:
            return { ...state, loading: false, authenticated: false };
        case AUTH_LOGOUT:
            return state;
        case AUTH_SIGNUP:
            return { ...state, isSignup: !state.isSignup };
        default:
            return state;
    }
}

export const login = ({ username, password }) => (dispatch) => {
    dispatch({ type: AUTH_REQUEST })

    const url = '/authenticate';

    api.post(url, { username: username, password: password })
        .then((response, ...other) => {
            localStorage.setItem('JWT_LOGIN', response.data)
            dispatch(go('/'))
            dispatch({ type: AUTH_RESPONSE, payload: response.data })
        })
        .catch(() => {
            //toast.error('Ocurrió un error');
            dispatch({ type: AUTH_ERROR, error: 'Las credenciales no son correctas, intente nuevamente.' })
        });
}

export const signup = ({ ...values }) => (dispatch) => {
    dispatch({ type: AUTH_REQUEST })

    const url = '/public/sign-up';
    
    api.post(url, values)
        .then((response) => {
            login({ username: values.username, password: values.password })(dispatch)
        })
        .catch(() => {
            //toast.error('Ocurrió un error');
            dispatch({ type: AUTH_ERROR, error: 'Las credenciales no son correctas, intente nuevamente.' })
        });
}

export const logout = () => (dispatch) => {
    localStorage.removeItem('JWT_LOGIN');
    dispatch(replace("/"))
    window.location.reload()
}

export const signupForm = () => (dispatch) => {
    dispatch({ type: AUTH_SIGNUP })
}