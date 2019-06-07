import { combineReducers } from 'redux'

import list from './list'
import view from './view'

export default combineReducers({
    list, view
});

export const getCategories = () => (dispatch) => {

}