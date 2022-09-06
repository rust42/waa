import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import {fetchActivityCountByActionIn24} from "../../reducers/ActivitiesSlice";

export default function ActivityCountByActionIn24() {

    const dispatch = useDispatch();
    const activityCountByActionIn24 = useSelector(state => state.activities.activityCountByActionIn24)

    useEffect(() => {
        dispatch(fetchActivityCountByActionIn24());
    }, [])
    const option = {
        xAxis: {
            type: 'category',
            data: activityCountByActionIn24.names,
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
                data: activityCountByActionIn24.numbers,
                type: 'bar'
            }
        ],
        title: {
            show: true,
            text: "Activity count by action in 24 hours",
            left: "center",
            top: "bottom",
        },
    };
    return (
        <div className="d-grid gap-3">
            <div className="p-2 bg-light border" style={{marginTop: "20px"}}>
                <ReactEcharts option={option}/>
            </div>
        </div>
    );
}