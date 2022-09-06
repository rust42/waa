import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {fetchJobsByLocation, fetchStudentsPerState} from "../../reducers/EchartsSlice";
import {useEffect} from "react";
import {fetchActivityCountByUserIn24} from "../../reducers/ActivitiesSlice";

export default function ActivityCountByUserIn24() {

    const dispatch = useDispatch();
    const activityCountByUserIn24 = useSelector(state => state.activities.activityCountByUserIn24)

    useEffect(() => {
        dispatch(fetchActivityCountByUserIn24());
    }, [])

    const option = {
        xAxis: {
            type: 'category',
            data: activityCountByUserIn24.names,
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: activityCountByUserIn24.numbers,
                type: 'bar'
            }
        ],
        title: {
            show: true,
            text: "Activity count by user in 24 hours",
            left: "center",
            top: "bottom",
        }
    };
    return (
        <div className="d-grid gap-3">
            <div className="p-2 bg-light border" style={{marginTop: "20px"}}>
                <ReactEcharts option={option}/>
            </div>
        </div>
    );
}