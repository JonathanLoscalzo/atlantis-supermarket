import { applyMiddleware, combineReducers, compose, createStore } from 'redux';
import thunk from 'redux-thunk';
import { connectRouter } from 'connected-react-router'
import { routerMiddleware } from 'connected-react-router'
import { reducer as formReducer } from 'redux-form'

// import client from '../modules/clients';
// import auth from '../modules/auth'
// import element from '../modules/element';
// import task from '../modules/task';
// import repair from '../modules/repair';

export default function configureStore(history, initialState) {
    const reducers = {
        form: formReducer,
        router: connectRouter(history)
        // auth,
        // client,
        // element, 
        // task,
        // repair
    };

    const middleware = [
        thunk,
        routerMiddleware(history)
    ];

    // In development, use the browser's Redux dev tools extension if installed
    const enhancers = [];
    const isDevelopment = process.env.NODE_ENV === 'development';
    if (isDevelopment && typeof window !== 'undefined' && window.devToolsExtension) {
        enhancers.push(window.devToolsExtension());
    }

    const rootReducer = combineReducers({
        ...reducers
    });

    return createStore(
        rootReducer,
        initialState,
        compose(applyMiddleware(...middleware), ...enhancers)
    );
}