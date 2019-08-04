import React from 'react'

import columns from './ColumnsConfig'
import ReactTable from 'react-table'
import {
    Col, Row
} from 'reactstrap'

const ProductList = (props) => {
    return (
        <Row>
            <Col md="12" className="pt-4">
                <ReactTable
                    {...props}
                    manual
                    data={props.data}
                    pages={props.pages}
                    loading={props.data_loading}
                    onFetchData={props.fetchData}
                    onPageSizeChange={props.onPageSizeChange}
                    columns={columns}
                    defaultPageSize={props.defaultPageSize}
                    className="-striped -highlight"
                    getTdProps={(state, row, col, instance) => ({
                        onClick: (event, cb) => {
                            // console.log(state, row, col, instance)
                        }
                    })}/>
            </Col>
        </Row >
    )
}

export default ProductList;