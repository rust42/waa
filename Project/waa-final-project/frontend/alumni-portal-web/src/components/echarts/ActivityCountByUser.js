import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import {fetchActivityCountByUser} from "../../reducers/ActivitiesSlice";

export default function ActivityCountByUser() {

    const dispatch = useDispatch();
    const activityCountByUser = useSelector(state => state.activities.activityCountByUser)

    useEffect(() => {
        dispatch(fetchActivityCountByUser());
    }, [])

    const option = {
        xAxis: {
            type: 'category',
            data: activityCountByUser.names,
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: activityCountByUser.numbers,
                type: 'bar'
            }
        ],
        title: {
            show: true,
            text: "Activity count by user",
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