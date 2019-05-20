import React from 'react'
import logo from './supermarket-logo.png'

export default class Sidebar extends React.Component {


    render() {

        return (
            <div class="sidebar" data-color="blue">

                <div class="logo">
                    <a href="http://www.creative-tim.com" class="simple-text logo-normal">
                        <img src={logo} />
                        Atlantis
                     </a>
                </div>

                <div class="sidebar-wrapper" id="sidebar-wrapper">
                    <ul class="nav">
                        <li class="">
                            <a href="../dashboard.html">
                                <i class="now-ui-icons design_app"></i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        )
    }
}