import { useKeycloak } from "@react-keycloak/web"
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import "../css/Nav.css"
import Notification from "../notifications/Notification";
import { getUserByEmail } from "../reducers/UserSlice";

export const ROLE ={
    STUDENT: 'STUDENT',
    FACULTY: 'FACULTY'
}

export const isFaculty = (roles)=>{
    return roles.includes(ROLE.FACULTY);
}

export default function Nav() {
    const { keycloak } = useKeycloak();
    const navigate = useNavigate();
    const user = useSelector((state)=> state.user.user);
    const dispatch = useDispatch();


    const goToProfile = ()=>{
        const userId = user.id;
        if(isFaculty(keycloak.realmAccess.roles)){
            navigate(`/faculties/${userId}`)
        }else{
            navigate(`/students/${userId}`)
        }
    }

    useEffect(()=>{
        if(keycloak?.idTokenParsed)
            dispatch(getUserByEmail(keycloak.tokenParsed.email));
    }, [keycloak?.idTokenParsed])

    const logout = ()=>{
        navigate('/')
        keycloak.logout()
    }


    return  <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
        <a className="navbar-brand" href="#">Alumni Portal</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                    <Link to="/" className="nav-link">Home</Link>
                </li>
            {!keycloak.authenticated && (
                <li className="nav-item">
                    <button onClick={keycloak.login} className="nav-link btn btn-primary">
                        Login</button>
                </li>
            )
            }
            {keycloak.authenticated && (
                <>
                <Notification />
                <li className="nav-item">
                    <Link to="/jobs" className="nav-link">Jobs</Link>
                </li>
                <li className="nav-item">
                    <Link to="/jobs/latest" className="nav-link">Latest Jobs</Link>
                </li>
                <li className="nav-item">
                    <Link to="/jobs/recentlyAppliedJobs" className="nav-link">Hot Jobs</Link>
                </li>
                {!isFaculty(keycloak.realmAccess.roles) ?
                <>
                    <li className="nav-item">
                        <Link to="/jobs/new" className="nav-link">Post Job</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/jobs/myJobs" className="nav-link">My Jobs</Link>
                    </li>
                </>
                :
                <>
                    <li className="nav-item">
                        <Link to="/students" className="nav-link">Students</Link>
                    </li>
                </>
                }
                <li className="nav-item">
                    <button
                        onClick= {()=>logout()} className="nav-link btn btn-primary">
                        Logout ({keycloak.tokenParsed.preferred_username})
                    </button>
                </li>
                <li className="nav-item">
                    <button className="nav-link btn btn-primary" onClick={goToProfile}>View profile</button>
                </li>
                <li className="nav-item">
                    <Link to="/echarts" className="nav-link">E-Charts</Link>
                </li>
                </>
            )}
            </ul>
        </div>
    </nav>;
};
