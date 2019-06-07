import React from 'react'
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { reduxForm, formValueSelector } from 'redux-form'

import { load, create, goBack } from '../index';

import Form from '../../form/Form'
import schema from '../../form/Validation';
import { validator } from '../../../../shared'

class CreatePage extends React.Component {

    componentWillMount() {
        this.props.load(this.props.match.params.id)
    }

    componentDidUpdate(prevProps) {
        if (this.props.pristine && !prevProps.pristine) {
            debugger;
        }
    }

    render() {
        return (
            <React.Fragment>
                <CreateForm
                    {...this.props}
                    title="Crear Product"
                    initialValues={this.props.element}
                    onSubmit={(values) => { this.props.create(values); }}
                />
            </React.Fragment>
        )
    }
}

const CreateForm = reduxForm({
    form: 'product/create',  // a unique identifier for this form
    validate: validator(schema),
    enableReinitialize: true
})(Form)

const selector = formValueSelector('product/create');

const mapStateToProps = ({ product: element, ...state }) => ({
    element: element.create.element,
    providers: element.create.providers,
    categories: element.create.categories,
    loading: element.create.loading,
    error: element.create.error,
})

const mapDispatchToProps = (dispatch) => bindActionCreators(
    {
        load,
        create,
        goBack,
    }, dispatch)

export default connect(mapStateToProps, mapDispatchToProps)(CreatePage)