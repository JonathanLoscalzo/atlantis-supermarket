import React from 'react'
import columns from './ColumnsConfig'
import ReactTable from 'react-table'
import _ from 'lodash'
import NuevoButton from '../../../../../shared/NewElement'
import { Input } from 'reactstrap'

const ProductList = (props) => {
    return (
        <div className="col">
            <div className="col">
                <NuevoButton goToCreate={props.goToCreate} /> <br />
            </div>
            <div className="col">
                <Input type="checkbox"
                    checked={props.checked}
                    onChange={props.handlePivot} />{' '}
                Pivotear
            </div>
            <br />
            <ReactTable
                {...props}
                manual
                data={props.data}
                pages={props.pages}
                loading={props.data_loading}
                onFetchData={props.fetchData}
                onPageSizeChange={props.onPageSizeChange}
                columns={columns.productColumns}
                pivotBy={props.pivotBy}
                defaultPageSize={props.defaultPageSize}
                className="-striped -highlight"
                // getTrProps={(state, rowInfo, column, instance) => {
                //     if (rowInfo && !rowInfo.aggregated) {
                //         let { original } = rowInfo;
                //         let lowStock = original.minStock < _.sum(original.batches.map(x => x.remainingUnits))
                //         return {
                //             style: {
                //                 background: lowStock ? '' : 'red'
                //             }
                //         }
                //     }

                //     return {
                //         style: {
                //             //background: true ? 'green' : 'red'
                //         }
                //     }

                // }}
                SubComponent={row => {
                    return (
                        <div style={{ padding: "20px" }}>
                            <h3> Lotes </h3>
                            <ReactTable
                                data={row.original.batches}
                                columns={columns.columnsBatch}
                                defaultPageSize={3}
                            />
                        </div>
                    );
                }}
            />
        </div>
    )
}

export default ProductList;