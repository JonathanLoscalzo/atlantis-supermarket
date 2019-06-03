import React from 'react';
import './styles.css';

const Spinner = ({ loading, ...props }) => {
    if (loading) {
        return (
            <div className="ml-auto mt-auto spinner-border text-primary" role="status">
                <span className="sr-only">Loading...</span>
            </div>
        )
    }
    else {
        return (props.children)
    }
}


export default Spinner;

