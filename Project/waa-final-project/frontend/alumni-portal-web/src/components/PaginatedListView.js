import LoadingState from "../reducers/LoadingState";
import LoadMoreButton from "./LoadMoreButton";
import Loading from "./Loading";


const PaginatedListView = ({items, ItemComponent, loading, page, totalPages, loadMore, infos: {errorText, emptyText} }) => {
    if (loading === LoadingState.PENDING && items.length === 0) {
        return <Loading />
    }

    if (loading === LoadingState.FAILED && items.length === 0) {
        return <h4 className="text-center" style={{color: 'red'}}>{errorText}</h4>;
    }

    if (loading === LoadingState.SUCCEEDED && items.length === 0) {
        return <h4 className="text-center">{emptyText}</h4>
    }

    const hasMorePages = totalPages - 1 > page;

    return <div>
        {items.map (item =>  <ItemComponent item={item} key={item.id} />)}
        <div className="col text-center">
            <LoadMoreButton loadingState={loading} hasMorePage={hasMorePages} loadMore={loadMore} />
        </div>
    </div>;
};

export default  PaginatedListView;
