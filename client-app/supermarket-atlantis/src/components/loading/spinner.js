import React from 'react';
import './styles.css';

const Spinner = ({ loading, ...props }) => {
    if (loading) {
        return (
            <div class="ml-auto spinner-border text-primary" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        )
    }
    else {
        return (props.children)
    }
}


export default Spinner;

