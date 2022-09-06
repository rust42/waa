import LoadingState from "../reducers/LoadingState";

const LoadMoreButton = ({loadingState, hasMorePage, loadMore}) => {
    if (!hasMorePage) {
        return <></>;
    }

    if (loadingState === LoadingState.PENDING) {
        return <div className="spinner-border text-primary" role="status">
        </div>;
    }
    return <button className="btn btn-primary py-3 px-5 mb-4" onClick={loadMore}>Load more</button>;
};

export default LoadMoreButton;