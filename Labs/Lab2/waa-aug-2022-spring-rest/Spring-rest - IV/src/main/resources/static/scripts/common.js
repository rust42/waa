const fetchContent = (url, noContent, content) => {
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const rootElement = document.getElementById("root");
            if (data.length === 0) {
                noContent(rootElement);
                return;
            }
            content(rootElement, data);
        });
};

export { fetchContent };

