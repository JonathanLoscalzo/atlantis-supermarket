import { combineReducers } from 'redux'

import list from './list'
import remove from './remove'
import update from './update'
import create from './create'

export default combineReducers({
    list, remove, update, create
});