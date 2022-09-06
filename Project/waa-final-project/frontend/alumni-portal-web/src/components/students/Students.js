import {useSelector, useDispatch } from "react-redux";
import {useEffect} from "react";
import {fetchStudents} from "../../reducers/StudentsSlice";
import StudentItem from "./StudentItem";
import StudentSearchBar from "./StudentSearchBar";
import {useState} from "react";
import { SearchBarContext } from '../providers/StudentSearchBarProvider';
import { fetchDepartments } from "../../reducers/DepartmentsSlice";
import { fetchCities } from "../../reducers/CitiesSlice";
import {fetchStatesReducer} from "../../reducers/StatesSlice";
import PaginatedListView from "../PaginatedListView";


const Students = () => {
    const { totalPages, page,  content, loading} = useSelector(state => state.student.students);

    const departments = useSelector(state => state.department.departments);
    const cities = useSelector(state => state.city.cities);
    const states = useSelector(state => state.states.states)

    const [city, setCity] = useState("");
    const [department, setDepartment] = useState("");
    const [searchText, setSearchText] = useState("");
    const [state, setState] = useState("");
    const [studentId, setStudentId] = useState("");

    const dispatch = useDispatch();

    function searchButtonClicked() {
        dispatch(fetchStudents({ searchText, city, department, state, studentId, page: 0}));
    }

    const loadMore = () => {
        dispatch(fetchStudents({ searchText, city, department, state, studentId, page: page + 1}));
    };


    useEffect(() => {
        dispatch(fetchStudents({searchText, city, department, state, studentId, page: 0}));
        dispatch(fetchDepartments());
        dispatch(fetchCities())
        dispatch(fetchStatesReducer());
    }, []);


    return <div>
        <SearchBarContext.Provider value={ {city, setCity, department, setDepartment, departments, cities, searchText, setSearchText ,
            setState,
            states,
            state,
            studentId,
            setStudentId} }>
            <StudentSearchBar>
                <div className="col-md-2">
                    <button className="btn btn-dark border-0 w-100" onClick={searchButtonClicked}>Search</button>
                </div>
            </StudentSearchBar>
        </SearchBarContext.Provider>
        <PaginatedListView page={page}
                           totalPages={totalPages}
                           loadMore={loadMore}
                           loading={loading}
                            items={content}
                           ItemComponent={StudentItem}
                           infos={{
                               errorText: 'Error occurred fetching students',
                               emptyText: 'No Students found.'
                                }
                           } />
    </div>

};

export default Students;