import React from 'react'

export default class Footer extends React.Component {

    render() {

        return (<React.Fragment>
            <footer class="footer">
                <div class="container-fluid">
                    <nav>
                        <ul>
                            <li>
                                <a href="http://presentation.creative-tim.com">
                                    About Us
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <div class="copyright" id="copyright">
                        © 2019
                    </div>
                </div>
            </footer>
        </React.Fragment>)
    }
}