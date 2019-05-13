! function() {
    function t(t) { this.init(t) }
    var e = { width: 280, height: 34, textMsg: "请按住滑块，拖动到最右边", successMsg: "验证成功", callback: function() {} };
    t.prototype = {
        init: function(t) { this.opts = this.extend(e, t), this.render() },
        render: function() {
            this.ele = document.getElementById(this.opts.id);
            var t = '<div class="ft-slider-content lock">' + this.opts.textMsg + '</div><div class="ft-slider-bar" style="transform:translate3d(0,0,0);"></div><div class="ft-slider-bg" id="ft_slider_bg"></div>',
                e = document.createElement("div");
            e.className = "ft-slider", e.innerHTML = t, this.ele.appendChild(e), this.setOptions(), this.drag()
        },
        setOptions: function() {
            var t = this.ele.getElementsByClassName("ft-slider")[0],
                e = t.getElementsByClassName("ft-slider-bar")[0],
                s = this.opts,
                i = "number" == typeof s.width ? s.width + "px" : s.width,
                n = "number" == typeof s.height ? s.height + "px" : s.height;
            t.style.width = i, t.style.height = n, t.style.lineHeight = t.offsetHeight + "px", e.style.width = t.offsetHeight + "px", e.style.height = t.offsetHeight + "px"
        },
        restore: function() {
            if (this.result) {
                var t = this.opts,
                    e = this.ele.getElementsByClassName("ft-slider")[0],
                    s = e.getElementsByClassName("ft-slider-bar")[0],
                    i = e.getElementsByClassName("ft-slider-content")[0],
                    n = e.getElementsByClassName("ft-slider-bg")[0];
                this.result = !1, i.className = "ft-slider-content lock", s.style.transform = "translate3d(0,0,0)", n.style.width = 0, s.className = "ft-slider-bar transition", i.textContent = t.textMsg
            }
        },
        drag: function() {
            var t = this.ele.getElementsByClassName("ft-slider-bar")[0];
            this.isDrag = !1, this.result = !1, this.start(t), this.move(t), this.end(t)
        },
        start: function(t) {
            var e = this,
                s = this.ele.getElementsByClassName("ft-slider-bg")[0];
            t.addEventListener("touchstart", function(t) { e.result || (this.className = "ft-slider-bar", s.className = "ft-slider-bg", e.isDrag = !0, e.strartX = t.touches[0].clientX, t.preventDefault()) }, !1)
        },
        move: function(t) {
            var e = this,
                s = this.ele.getElementsByClassName("ft-slider")[0],
                i = s.getElementsByClassName("ft-slider-bg")[0];
            t.addEventListener("touchmove", function(t) {
                if (e.isDrag && !e.result) {
                    var n = t.touches[0].clientX - e.strartX,
                        l = s.offsetWidth - s.offsetHeight;
                    n = n > 0 ? n > l ? l : n : 0, this.style.transform = "translate3d(" + n + "px,0,0)", i.style.width = n + "px", t.preventDefault()
                }
            }, !1)
        },
        end: function(t) {
            var e = this,
                s = this.ele.getElementsByClassName("ft-slider")[0],
                i = s.getElementsByClassName("ft-slider-bg")[0],
                n = s.getElementsByClassName("ft-slider-content")[0],
                i = s.getElementsByClassName("ft-slider-bg")[0];
            t.addEventListener("touchend", function(t) {
                if (e.isDrag) {
                    var l = e.opts,
                        a = l.callback,
                        r = t.changedTouches[0].clientX - e.strartX,
                        d = s.offsetWidth - s.offsetHeight,
                        h = 0;
                    r >= d ? (h = d, n.textContent = l.successMsg, this.className = this.className += " success", n.className = "ft-slider-content success", e.result = !0) : e.result = !1, this.className = e.result ? "ft-slider-bar success transition" : "ft-slider-bar transition", i.className = "ft-slider-bg transition", i.style.width = h + "px", this.style.transform = "translate3d(" + h + "px,0,0)", e.isDrag = !1, a && a(e.result), t.preventDefault()
                }
            }, !1)
        },
        extend: function(t, e) {
            var s = {};
            if (!e.id) throw "id is required";
            for (var i = 0; i < arguments.length; i++)
                for (var option in arguments[i]) s[option] = arguments[i][option];
            return s
        },
        getTranslateX: function(t) { return parseInt(t.match(/-?\d+(?=px)/g)[0]) }
    }, window.FtSlider = t;
}();