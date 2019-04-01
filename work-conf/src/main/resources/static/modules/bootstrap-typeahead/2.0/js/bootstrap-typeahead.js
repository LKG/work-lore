﻿//
//
//  bootstrap-typeahead.js
//
//  Bootstrap Typeahead+ v2.0
//  Terry Rosen
//  https://github.com/tcrosen/twitter-bootstrap-typeahead
//
//

!
function ($) {

  'use strict';

  var _defaults = {
      source: [],
      maxResults: 10,
      minLength: 1,
      menu: '<ul class="typeahead dropdown-menu"></ul>',
      item: '<li><a href="#"></a></li>',
      display: 'name',
      key: null,
      val: 'id',
      itemSelected: function () { }
    },

    _keyCodes = {
      DOWN: 40,
      ENTER: 13 || 108,
      ESCAPE: 27,
      TAB: 9,
      UP: 38
    },

    Typeahead = function (element, options) {
      this.$element = $(element);
      this.options = $.extend(true, {}, $.fn.typeahead.defaults, options);
      this.$menu = $(this.options.menu).appendTo('body');
      this.sorter = this.options.sorter || this.sorter;
      this.highlighter = this.options.highlighter || this.highlighter;
      this.shown = false;
      this.initSource();
      this.listen();
    }

  Typeahead.prototype = {

      constructor: Typeahead,

      initSource: function() {
        if (this.options.source) {
          if (typeof this.options.source === 'string') {
           this.source = $.extend({}, $.ajaxSettings, { url: this.options.source })
          } else if (typeof this.options.source === 'object') {
            if (this.options.source instanceof Array) {
              this.source = this.options.source;
            } else {
              this.source = $.extend(true, {}, $.ajaxSettings, this.options.source);
            }
          }
        }
      },

      eventSupported: function(eventName) {
        var isSupported = (eventName in this.$element);

        if (!isSupported) {
          this.$element.setAttribute(eventName, 'return;');
          isSupported = typeof this.$element[eventName] === 'function';
        }

        return isSupported;
      },

      lookup: function (event) {
        var that = this,
            items;

        this.query = this.$element.val();
        if (!this.query || this.query.length < this.options.minLength) {
          return this.shown ? this.hide() : this;
        }

        if (this.source.url) {
          if (this.xhr) this.xhr.abort();
          //扩展ajax 方便定制 by lkg 
          var p=this.$element.attr("name");
          if(!this.options.key){
         	 this.options.key=p;
          }
          var data={};
          data[this.options.key+'']=that.query ;
          this.xhr = $.ajax(
            $.extend({}, this.source, {
              data: data,
              success: function(data) {
            	  var source=that.source.success.call(this, data);
            	  items = $.proxy(that.filter(source), that);
              }
            })
          );
         // alert(items);
          //扩展ajax 方便定制 by lkg
        } else {
          items = $.proxy(that.filter(that.source), that);
        }
      },

      filter: function(data) {
        var that = this,
            items;
        items = $.grep(data, function (item) {
          return ~item[that.options.display].toLowerCase().indexOf(that.query.toLowerCase());
        });

        if (!items || !items.length) {
          return this.shown ? this.hide() : this;
        } else {
          items = items.slice(0, this.options.maxResults);
        }

        return this.render(this.sorter(items)).show();
      },

      sorter: function (items) {
        var that = this,
            beginswith = [],
            caseSensitive = [],
            caseInsensitive = [],
            item;

        while (item = items.shift()) {
          if (!item[that.options.display].toLowerCase().indexOf(this.query.toLowerCase())) {
            beginswith.push(item);
          } else if (~item[that.options.display].indexOf(this.query)) {
            caseSensitive.push(item);
          } else {
            caseInsensitive.push(item);
          }
        }

        return beginswith.concat(caseSensitive, caseInsensitive);
      },

      show: function () {
        var pos = $.extend({}, this.$element.offset(), {
            height: this.$element[0].offsetHeight
        });

        this.$menu.css({
            top: pos.top + pos.height,
            left: pos.left
        });

        this.$menu.show();
        this.shown = true;
        return this;
      },

      hide: function () {
        this.$menu.hide();
        this.shown = false;
        return this;
      },

      highlighter: function (text) {
        var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&');
        return text.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
          return '<strong>' + match + '</strong>';
        });
      },

      render: function (items) {
        var that = this,
          $templateItem,
          $standardItem;

        items = $(items).map(function (i, item) {
            if (that.options.tmpl) {
              i = $(that.options.tmpl(item));
            } else {
              i = $(that.options.item);
            }

            if (typeof that.options.val === 'string') {
              i.attr('data-value', item[that.options.val]);
            } else {
              i.attr('data-value', JSON.stringify($.extend({}, that.options.val, item)))
            }

            $templateItem = i.find('.typeahead-display-val');
            $standardItem = i.find('a');

            if ($templateItem.length) {
              $templateItem.html(that.highlighter(item[that.options.display]))
            } else if ($standardItem.length) {
              $standardItem.html(that.highlighter(item[that.options.display]));
            }

            return i[0];
        });

        items.first().addClass('active');

        setTimeout(function() {
          that.$menu.html(items);
        }, 250)

        return this;
      },

      select: function () {
        var $selectedItem = this.$menu.find('.active');
        this.$element.val($selectedItem.text()).change();
        //	修改  by lkg
        var dataValue=$selectedItem.attr('data-value');
        if(dataValue){
            this.options.itemSelected(JSON.parse(dataValue));
        }else{
        	  this.options.itemSelected(dataValue);
        }
        return this.hide();
      },

      next: function (event) {
        var active = this.$menu.find('.active').removeClass('active');
        var next = active.next();

        if (!next.length) {
          next = $(this.$menu.find('li')[0]);
        }

        next.addClass('active');
      },

      prev: function (event) {
        var active = this.$menu.find('.active').removeClass('active');
        var prev = active.prev();

        if (!prev.length) {
          prev = this.$menu.find('li').last();
        }

        prev.addClass('active');
      },

      listen: function () {
          this.$element
            .on('blur', $.proxy(this.blur, this))
            .on('keyup', $.proxy(this.keyup, this));

          if (this.eventSupported('keydown')) {
            this.$element.on('keydown', $.proxy(this.keypress, this));
          } else {
            this.$element.on('keypress', $.proxy(this.keypress, this));
          }

          this.$menu
            .on('click', $.proxy(this.click, this))
            .on('mouseenter', 'li', $.proxy(this.mouseenter, this));
      },

      keyup: function (e) {
        e.stopPropagation();
        e.preventDefault();

        switch (e.keyCode) {
          case _keyCodes.DOWN:
          case _keyCodes.UP:
             break;
          case _keyCodes.TAB:
          case _keyCodes.ENTER:
            if (!this.shown) return;
            this.select();
            break;
          case _keyCodes.ESCAPE:
            this.hide();
            break;
          default:
            this.lookup();
        }
      },

      keypress: function (e) {
        e.stopPropagation();

        if (!this.shown) return;

        switch (e.keyCode) {
          case _keyCodes.TAB:
          case _keyCodes.ESCAPE:
          case _keyCodes.ENTER:
            e.preventDefault();
            break;
          case _keyCodes.UP:
            e.preventDefault();
            this.prev();
            break;
          case _keyCodes.DOWN:
            e.preventDefault();
            this.next();
            break;
        }
      },

      blur: function (e) {
        var that = this;
        e.stopPropagation();
        e.preventDefault();
        setTimeout(function () {
          if (!that.$menu.is(':focus')) {
            that.hide();
          }
        }, 150);
      },

      click: function (e) {
        e.stopPropagation();
        e.preventDefault();
        this.select();
      },

      mouseenter: function (e) {
        this.$menu.find('.active').removeClass('active');
        $(e.currentTarget).addClass('active');
      }
  }

  //  Plugin definition
  $.fn.typeahead = function (option) {
    return this.each(function () {
      var $this = $(this),
          data = $this.data('typeahead'),
          options = typeof option === 'object' && option;

      if (!data) {
          $this.data('typeahead', (data = new Typeahead(this, options)));
      }

      if (typeof option === 'string') {
          data[option]();
      }
    });
  }

  $.fn.typeahead.defaults = _defaults;
  $.fn.typeahead.Constructor = Typeahead;

  //  Data API (no-JS implementation)
  $(function () {
    $('body').on('focus.typeahead.data-api', '[data-provide="typeahead"]', function (e) {
      var $this = $(this);
      if ($this.data('typeahead')) return;
      e.preventDefault();
      $this.typeahead($this.data());
    })
  });
} (window.jQuery);
