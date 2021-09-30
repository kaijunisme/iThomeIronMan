$(() => {
    $(".background-gray").on("click", function() {
        $(".active").removeClass("active");
    });

    $(".mobile-menu").on("click", function() {
        $(".background-gray").addClass("active");
        $(".mobile-menu-content").addClass("active");
    });
})