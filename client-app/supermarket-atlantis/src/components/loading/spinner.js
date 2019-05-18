import React from 'react';
import './styles.css';

const Spinner =({ loading, ...props }) => {
    if (loading) {
        return (<div className="spinner" >
            <div className="bounce1"></div>
            <div className="bounce2"></div>
            <div className="bounce3"></div>
        </div>)
    }
    else {
        return (props.children)
    }
}


export default Spinner;

