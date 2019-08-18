import React from 'react'
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { loaded } from '../index'
import Layout from '../../../components/Layout';
import { isAdmin } from 'common/auth';

class HomePage extends React.Component {

    componentDidMount() {
        this.props.loaded();
    }

    render() {
        const { loaded } = this.props;
        if (isAdmin()) {

            return (
                <div className="row">
                    <div className="col-md-10">
                        <div className="card">
                            <div className="card-header">
                                <h5 className="title">Ventas</h5>
                            </div>
                            <div className="card-body">
                                <iframe
                                    width="600"
                                    height="300"
                                    seamless
                                    frameBorder="0"
                                    scrolling="no"
                                    src="http://127.0.0.1:8088/superset/explore/?form_data=%7B%22datasource%22%3A%2212__table%22%2C%22viz_type%22%3A%22dist_bar%22%2C%22slice_id%22%3A76%2C%22url_params%22%3A%7B%7D%2C%22granularity_sqla%22%3Anull%2C%22time_grain_sqla%22%3A%22P1D%22%2C%22time_range%22%3A%22Last+week%22%2C%22metrics%22%3A%5B%22count%22%5D%2C%22adhoc_filters%22%3A%5B%5D%2C%22groupby%22%3A%5B%22name%22%5D%2C%22columns%22%3A%5B%5D%2C%22row_limit%22%3A10000%2C%22contribution%22%3Afalse%2C%22color_scheme%22%3A%22d3Category10%22%2C%22show_legend%22%3Atrue%2C%22show_bar_value%22%3Atrue%2C%22bar_stacked%22%3Afalse%2C%22order_bars%22%3Afalse%2C%22y_axis_format%22%3A%22.3s%22%2C%22y_axis_label%22%3A%22%22%2C%22show_controls%22%3Afalse%2C%22x_axis_label%22%3A%22prods%22%2C%22bottom_margin%22%3A50%2C%22x_ticks_layout%22%3A%2245%C2%B0%22%2C%22reduce_x_ticks%22%3Atrue%7D&standalone=true&height=200"
                                >
                                </iframe>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-2">
                        <div className="card">
                            <div className="card-header">
                                <h5 className="title">Ganancias último mes</h5>
                            </div>
                            <div className="card-body">
                                <iframe
                                    width="200"
                                    height="100"
                                    seamless
                                    frameBorder="0"
                                    scrolling="yes"
                                    src="http://127.0.0.1:8088/superset/explore/?form_data=%7B%22datasource%22%3A%2212__table%22%2C%22viz_type%22%3A%22big_number_total%22%2C%22slice_id%22%3A77%2C%22url_params%22%3A%7B%7D%2C%22granularity_sqla%22%3A%22created_date%22%2C%22time_grain_sqla%22%3Anull%2C%22time_range%22%3A%22Last+month%22%2C%22metric%22%3A%7B%22expressionType%22%3A%22SIMPLE%22%2C%22column%22%3A%7B%22id%22%3A445%2C%22column_name%22%3A%22profit%22%2C%22verbose_name%22%3A%22%22%2C%22description%22%3A%22ganancias%22%2C%22expression%22%3A%22%22%2C%22filterable%22%3Atrue%2C%22groupby%22%3Afalse%2C%22is_dttm%22%3Afalse%2C%22type%22%3A%22DECIMAL%2819%2C2%29%22%2C%22database_expression%22%3A%22%22%2C%22python_date_format%22%3A%22%22%2C%22optionName%22%3A%22_col_profit%22%7D%2C%22aggregate%22%3A%22SUM%22%2C%22sqlExpression%22%3Anull%2C%22hasCustomLabel%22%3Afalse%2C%22fromFormData%22%3Atrue%2C%22label%22%3A%22SUM%28profit%29%22%2C%22optionName%22%3A%22metric_7n6be0x99ly_hskrjv9gn%22%7D%2C%22adhoc_filters%22%3A%5B%5D%2C%22y_axis_format%22%3A%22.3s%22%7D&standalone=true&height=100"
                                >
                                </iframe>
                            </div>
                        </div>
                    </div>
                </div >
            )
        } else {
            return (
                <div>
                    <div className="col-md-12">
                        <div className="card">
                            <div className="card-header">
                                <h5 className="title">Bienvenido! </h5>
                            </div>
                            <div className="card-body">
                                Puede revisar sus ventas o comprar nuevos elementos
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    }
}

const mapStateToProps = ({ home }) => {
    return ({
        loading: home.loading
    });
};

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ loaded }, dispatch),
})
export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
