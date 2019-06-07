
const ACTION = "EXAMPLE/ACTION"

const initialState = {
    loading: false,
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case ACTION:
            return { ...state };
        default:
            return state;
    }
}

export const action = ({ id }) => (dispatch) => {
    // buscar id
}