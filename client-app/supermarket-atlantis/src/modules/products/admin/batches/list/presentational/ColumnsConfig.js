import React from 'react'
import _ from 'lodash'
import batchTypes from '../../../shared/batchTypes'
import renderToolbar from './Toolbar'
import renderToolbarBatches from './ToolbarBatches'
import moment from 'moment'

/*sku
upc
name
brand
minStock
providerPrice
retailPrice
description*/

const columns = [
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Marca
              </div>
        ),
        //accessor: 'brand',
        id: 'brand',
        Cell: props => props.original.brand
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Nombre
              </div>
        ),
        //accessor: 'name',
        id: 'name',
        Cell: props => props.original.name
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Tipo Lote
              </div>
        ),
        accessor: 'type',
        PivotValue: ({ value }) =>
            (<span style={{ color: "darkred" }}>
                {batchTypes[value].text}
            </span>),
        Cell: props => {
            return batchTypes[props.value].text
        }
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Min Stock
              </div>
        ),
        id: "minStock",
        Cell: props => {
            return props.original.minStock
        }
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Stock
              </div>
        ),
        id: "sum",
        Cell: props => {
            let stock = _.sum(props.original.batches.map(x => x.remainingUnits))
            return <div style={{
                backgroundColor: props.original.minStock > stock ? 'yellow' : ''
            }}>{stock}</div>
        }
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Opciones Producto
              </div>
        ),
        accessor: 'id',
        Aggregated: row => {
            return (
                <span>

                </span>
            )
        },
        Cell: props => renderToolbar(props.value)
    },
]

const columnsBatch = [
    {
        Header: "Detalle",
        accessor: "detail"
    },
    {
        Header: "Entrada",
        accessor: "entry",
        Cell: props => moment(props.value).format("DD/MM/YYYY")
    },
    {
        Header: "Expiración",
        accessor: "expiration",
        Cell: props => {
            return <div style={{
                backgroundColor: moment(props.value) <= moment() ? 'red' : ''
            }}>
                {moment(props.value).format("DD/MM/YYYY")}
            </div>
        },
    },
    {
        Header: "Remanente",
        accessor: "remainingUnits"
    },
    {
        Header: () => (
            <div style={{
                textAlign: "left",
                fontWeight: 'bold'
            }}>
                Opciones Lote
          </div>
        ),
        accessor: 'id',
        Aggregated: row => {
            return (
                <span>

                </span>
            )
        },
        Cell: props => {
            return renderToolbarBatches(props.value)
        }
    }
]

export default {
    productColumns: columns,
    columnsBatch
};