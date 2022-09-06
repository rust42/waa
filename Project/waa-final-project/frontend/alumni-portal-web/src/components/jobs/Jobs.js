import JobSearchBar from "./JobSearchBar";
import JobItem from "./JobItem";
import {SearchJobBarContext} from "../providers/JobSearchBarProvider";
import {useDispatch, useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {fetchStatesReducer} from "../../reducers/StatesSlice";
import {fetchTags} from "../../reducers/TagsSlice";
import {fetchJobsCities, searchJob} from "../../reducers/JobsSlice";
import PaginatedListView from "../PaginatedListView";

const Jobs = () => {
    const { totalPages, page,  content, loading} = useSelector(state => state.jobs.jobs);
    const cities = useSelector(state => state.jobs.cities);
    const tags = useSelector(state => state.tags.tags);
    const states = useSelector(state => state.states.states);

    const dispatch = useDispatch();


    const [tagChoosing, setTagChoosing] = useState("");
    const [tagOption, setTagOption] = useState([]);

    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [companyName, setCompanyName] = useState("");

    function searchButtonClicked() {
        if (tagChoosing === "All Tags") {
            dispatch(searchJob({state, city, companyName, page: 0}));
        } else {
            dispatch(searchJob({tagChoosing, state, city, page: 0}));
        }
    }

    const loadMore = () => {
        dispatch(searchJob({tagChoosing, state, city, companyName, page: page + 1}));
    };

    useEffect(() => {
        dispatch(searchJob({tagChoosing, state, city, companyName, page: 0}));
    }, [])


    useEffect(() => {
        dispatch(fetchJobsCities())
        dispatch(fetchStatesReducer())
        dispatch(fetchTags())
    }, [])


    useEffect(() => {
        let tagsOption = [{value: 0, label: "All Tags"}];
        tags.forEach(tag => {
            tagsOption.push({
                value: tag.id,
                label: tag.tagName,
            })
        })
        setTagOption(tagsOption);
    }, [tags])

    return (
        <div className="container-xxl bg-white p-0">
            <SearchJobBarContext.Provider value={{
                city,
                setCity,
                state,
                setState,
                cities,
                tags,
                states,
                companyName,
                setCompanyName,
                tagChoosing,
                setTagChoosing,
                tagOption,
                setTagOption,
            }}>
                <JobSearchBar>
                    <button className="btn btn-dark border-0 w-100" onClick={searchButtonClicked}>Search</button>
                </JobSearchBar>
            </SearchJobBarContext.Provider>

            <PaginatedListView page={page}
                               totalPages={totalPages}
                               loadMore={loadMore}
                               loading={loading}
                               items={content}
                               ItemComponent={JobItem}
                               infos={{
                                   errorText: 'Error occurred fetching jobs',
                                   emptyText: 'No Job Advertisements found.'
                               }} />
        </div>
    )
};

export default Jobs;