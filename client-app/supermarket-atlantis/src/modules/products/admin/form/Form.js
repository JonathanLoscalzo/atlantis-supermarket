import React from 'react'
import { Field } from 'redux-form';
import { Row, FormGroup } from 'reactstrap'

import FormToolbar from './Toolbar';
import { Spinner } from '../../../shared'


import { RenderField, RenderSelectableField } from '../../../shared'
import batchTypes from '../shared/batchTypes'

const types = [
    { value: batchTypes.DEFAULT.enum, label: batchTypes.DEFAULT.text },
    { value: batchTypes.EXPIRATION.enum, label: batchTypes.EXPIRATION.text }
]
export default props => {
    const {
        handleSubmit,
        title,
        providers,
        categories,
    } = props

    let providersOptions = providers.map(x => ({ value: x.id, label: x.name }))
    providersOptions.push({ value: "", label: "Seleccione" })

    let categoriesOptions = categories.map(x => ({ value: x.id, label: x.description }))

    return (
        <div>
            <h5> {title} </h5>
            <div>
                <form onSubmit={handleSubmit}>
                    <Row>
                        <FormGroup className="col-3">
                            <Field label="SKU" name="sku"
                                placeholder="sku"
                                component={RenderField}
                                type="text" />
                            <Field label="Cód. Barras" name="upc"
                                placeholder="upc"
                                component={RenderField}
                                type="text" />
                            <Field label="Nombre"
                                name="name"
                                placeholder="name"
                                component={RenderField}
                                type="text" />
                            <Field label="Marca/compañia" name="brand"
                                placeholder="brand"
                                component={RenderField}
                                type="text" />
                            <Field label="Expiración" name="expiration"
                                placeholder="expiration"
                                component={RenderField}
                                type="date" />
                            <Field
                                label="Tipo Lote"
                                name="type"
                                //placeholder="Seleccione..."
                                options={types}
                                component={RenderSelectableField} />
                        </FormGroup>
                        <FormGroup className="col-3">
                            <Field label="Stock minimo" name="minStock"
                                placeholder="minStock"
                                component={RenderField}
                                type="text" />
                            <Field label="Stock" name="stock"
                                placeholder="stock"
                                component={RenderField}
                                type="text" />
                            <Field label="Precio costo" name="providerPrice"
                                placeholder="providerPrice"
                                component={RenderField}
                                type="text" />
                            <Field label="Precio venta" name="retailPrice"
                                placeholder="retailPrice"
                                component={RenderField}
                                type="text" />
                            <Field label="Proveedor"
                                name="providerId"
                                placeholder="Proveedor"
                                options={providersOptions}
                                component={RenderSelectableField} />
                        </FormGroup>
                        <FormGroup className="col-4">
                            <Field label="Descripcion" name="description"
                                placeholder="description"
                                component={RenderField}
                                type="textarea" />
                            <Field label="Descripcion Lote" name="batchDetails"
                                placeholder="batchDetails"
                                component={RenderField}
                                type="textarea" />
                            <Field
                                label="Categorias"
                                name="categories"
                                multiple
                                //placeholder="Seleccione..."
                                options={categoriesOptions}
                                component={RenderSelectableField} />
                        </FormGroup>
                    </Row>
                    <Spinner loading={props.loading}>
                        <FormToolbar {...props} />
                    </Spinner>

                </form>
            </div>
        </div>
    )
}