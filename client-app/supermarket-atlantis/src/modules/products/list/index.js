
const PRODUCT_DEFAULT = "PRODUCT/DEFAULT"

const initialState = {
    loading: false,
    authenticated: false,
    errorMessage: null
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case PRODUCT_DEFAULT:
            return { ...state };
        default:
            return state;
    }
}

export const get_products = ({ }) => (dispatch) => {
    dispatch({ type: PRODUCT_DEFAULT })
    // dispatch({ type: AUTH_REQUEST })

    //const url = 'api/authenticate';

    /*api.post(url, { username: username, password: password })
        .then((response) => {
            debugger;
            localStorage.setItem('JWT_LOGIN', response.data)
            dispatch(replace('/'))
            dispatch({ type: AUTH_RESPONSE, payload: response.data })
        })
        .catch(() => {
            //toast.error('Ocurrió un error');
            dispatch({ type: AUTH_ERROR, error: 'Las credenciales no son correctas, intente nuevamente.' })
        });*/

}