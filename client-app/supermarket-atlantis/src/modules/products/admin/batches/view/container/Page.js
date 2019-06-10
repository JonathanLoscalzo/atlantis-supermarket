import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { getElement, goBack } from '../index'
import Presentation from '../presentation/Presentation'
import { Spinner } from '../../../../../shared'

const ViewPage = (props) => {
    useEffect(() => {
        props.getElement(props.match.params.id)
    }, [props.match.params.id])

    return (
        <Spinner loading={props.loading}>
            <Presentation {...props} />
        </Spinner>
    )
}

const mapStateToProps = ({ product: entity }) => {
    return ({
        element: entity.view.element,
        loading: entity.view.loading
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ getElement, goBack }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ViewPage)


