import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import {fetchActivityCountByAction, fetchActivityCountByActionIn24} from "../../reducers/ActivitiesSlice";

export default function ActivityCountByAction() {

    const dispatch = useDispatch();
    const activityCountByAction = useSelector(state => state.activities.activityCountByAction);

    useEffect(() => {
        dispatch(fetchActivityCountByAction());
    }, [])

    const option = {
        xAxis: {
            type: 'category',
            data: activityCountByAction.names,
            axisLabel:{
                show:true,
                rotate:-20,
                showMaxLabel:true
            }
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: activityCountByAction.numbers,
                type: 'bar'
            }
        ],
        title: {
            show: true,
            text: "Activity count by action",
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