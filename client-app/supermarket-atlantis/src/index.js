// import React from 'react';
// import ReactDOM from 'react-dom';
// import './index.css';
// import App from './App';
// import * as serviceWorker from './serviceWorker';

// ReactDOM.render(<App />, document.getElementById('root'));

// // If you want your app to work offline and load faster, you can change
// // unregister() to register() below. Note this comes with some pitfalls.
// // Learn more about service workers: https://bit.ly/CRA-PWA
// serviceWorker.unregister();

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle';
import 'bootstrap/dist/js/bootstrap'
// import './vendor/sb-admin-2.css'
// import 'react-table/react-table.css'
import 'react-widgets/dist/css/react-widgets.css';
import "react-table/react-table.css";

import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'

import { createBrowserHistory } from 'history';
import { PersistGate } from 'redux-persist/integration/react'
import configureStore from './store/configureStore';
import { ConnectedRouter } from 'connected-react-router'
import App from './modules/app/App'
import 'react-toastify/dist/ReactToastify.css';
import menu from './menu';

const baseUrl = document.getElementsByTagName('base')[0].getAttribute('href');
const history = createBrowserHistory({ basename: baseUrl });
const initialState = window.initialReduxState;
const { store, persistor } = configureStore(history, initialState);

ReactDOM.render(
  <Provider store={store}>
    {/* <PersistGate loading={null} persistor={persistor}> */}
      <ConnectedRouter history={history}>
        <App menu={menu} />
      </ConnectedRouter>
    {/* </PersistGate> */}
  </Provider>,
  document.getElementById('root')
)
