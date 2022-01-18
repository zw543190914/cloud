module.exports = {
    pages: {
        index: {
          // page 的入口
          entry: 'src/main.js',
          // 模板来源
          //template: 'public/index.html',
          // 在 dist/index.html 的输出
          //filename: 'index.html',
          // 当使用 title 选项时，
          // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
          //title: 'Index Page',
          // 在这个页面中包含的块，默认情况下会包含
          // 提取出来的通用 chunk 和 vendor chunk。
          //chunks: ['chunk-vendors', 'chunk-common', 'index']
        },
        // 当使用只有入口的字符串格式时，
        // 模板会被推导为 `public/subpage.html`
        // 并且如果找不到的话，就回退到 `public/index.html`。
        // 输出文件名会被推导为 `subpage.html`。
        //subpage: 'src/subpage/main.js'
      },
      // 关闭语法检查
    lintOnSave:false,

      // 代理服务器--不能配置多个代理
     /*  devServer: {
        proxy: 'http://localhost:9040'
      }
      */

      // 代理服务器 方式2
    devServer: {
        port:8087,
        proxy: {
          '/vue': {
            target: 'http://localhost:18092',
            pathRewrite:{'^/vue':''},
            ws: true,
            changeOrigin: true
          },
          '/websocket': {
            target: 'ws://localhost:18888',
            pathRewrite:{'^/websocket':''},
            ws: true,
            changeOrigin: true
          }
         /*  '/foo': {
            target: '<other_url>'
          } */
        },
      },
    publicPath:"./",
    /*css: {
      loaderOptions: {
        postcss: {
          plugins: [
            require('postcss-pxtorem')({ // 把px单位换算成rem单位
              rootValue: 75, // 换算的基数(设计图750的根字体为75)
              // selectorBlackList: ['weui', 'mu'], // 忽略转换正则匹配项
              propList: ['*']//!*代表将项目中的全部进行转换，单个转换如width、height等
            })
          ]
        }
      }
    },
    configureWebpack: config => {
      if (process.env.NODE_ENV === 'production') {
        // 为生产环境修改配置...
      } else {
        // 为开发环境修改配置...
      }
    }*/
}
