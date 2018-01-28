function changeLang(locale) {
    var url = "main?command=changeLocale&locale=" + locale;
    $.ajax({
        url: url,
        method: "post"
    });
    resetPage();
}

function resetPage() {
    location.reload();
}