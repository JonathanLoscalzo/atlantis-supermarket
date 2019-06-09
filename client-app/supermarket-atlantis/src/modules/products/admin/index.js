import { combineReducers } from 'redux'

import list from './list'
import view from './view'
import create from './create'
import update from './update'

export default combineReducers({
    list, view, create, update
});

export const getCategories = () => (dispatch) => {

}