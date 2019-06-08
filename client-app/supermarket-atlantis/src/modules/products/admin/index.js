import { combineReducers } from 'redux'

import list from './list'
import view from './view'
import create from './create'

export default combineReducers({
    list, view, create,
});

export const getCategories = () => (dispatch) => {

}