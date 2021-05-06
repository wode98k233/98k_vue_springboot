<template>
  <div>
    <!--标题-->
    <div>
    <h1>这里写博客</h1>
    <el-input placeholder="请输入内容" style="width:600px" v-model="input1">
      <template slot="prepend">标题</template>
    </el-input>
    </div>
    <!--Tag-->
    <div>
    <el-tag
      :key="tag"
      v-for="tag in dynamicTags"
      closable

      :disable-transitions="false"
      @close="handleClose(tag)">
      {{ tag }}
    </el-tag>
    <el-input
      class="input-new-tag"
      v-if="inputVisible"
      v-model="inputValue"
      ref="saveTagInput"
      size="small"
      @keyup.enter.native="handleInputConfirm"
      @blur="handleInputConfirm"
    >
    </el-input>
    <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
    </div>
    <!--上传-->
    <div>
      <h2>封面</h2>
    <el-upload
      action=""
      list-type="picture-card"
      :on-change="Add"
      :auto-upload="false"
      :multiple="false"
      :limit="1">
      <i slot="default" class="el-icon-plus" ></i>
      <div slot="file" slot-scope="{file}" >
        <img
          class="el-upload-list__item-thumbnail"
          :src="file.url" alt=""
        >
        <span class="el-upload-list__item-actions">
          <span
          class="el-upload-list__item-preview"
          @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>

        </span>
      </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible" class="file" type="file" >
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
    </div>
    <!--正文-->
    <!--富文本-->
    <div>
      <el-card style="height: 610px;">
        <quill-editor v-model="content" ref="myQuillEditor" style="height: 500px;" :options="editorOption"
                      @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
                      @change="onEditorChange($event)"
        >
          <!-- 自定义toolar -->
          <div id="toolbar" slot="toolbar">
            <!-- Add a bold button -->
            <button class="ql-bold" title="加粗">Bold</button>
            <button class="ql-italic" title="斜体">Italic</button>
            <button class="ql-underline" title="下划线">underline</button>
            <button class="ql-strike" title="删除线">strike</button>
            <button class="ql-blockquote" title="引用"></button>
            <button class="ql-code-block" title="代码"></button>
            <button class="ql-header" value="1" title="标题1"></button>
            <button class="ql-header" value="2" title="标题2"></button>
            <!--Add list -->
            <button class="ql-list" value="ordered" title="有序列表"></button>
            <button class="ql-list" value="bullet" title="无序列表"></button>
            <!-- Add font size dropdown -->
            <select class="ql-header" title="段落格式">
              <option selected>段落</option>
              <option value="1">标题1</option>
              <option value="2">标题2</option>
              <option value="3">标题3</option>
              <option value="4">标题4</option>
              <option value="5">标题5</option>
              <option value="6">标题6</option>
            </select>
            <select class="ql-size" title="字体大小">
              <option value="10px">10px</option>
              <option value="12px">12px</option>
              <option value="14px">14px</option>
              <option value="16px" selected>16px</option>
              <option value="18px">18px</option>
              <option value="20px">20px</option>
            </select>
            <select class="ql-font" title="字体">
              <option value="SimSun">宋体</option>
              <option value="SimHei">黑体</option>
              <option value="Microsoft-YaHei">微软雅黑</option>
              <option value="KaiTi">楷体</option>
              <option value="FangSong">仿宋</option>
              <option value="Arial">Arial</option>
            </select>
            <!-- Add subscript and superscript buttons -->
            <select class="ql-color" value="color" title="字体颜色"></select>
            <select class="ql-background" value="background" title="背景颜色"></select>
            <select class="ql-align" value="align" title="对齐"></select>
            <button class="ql-clean" title="还原"></button>
            <!-- You can also add your own -->
            <button class="ql-link" title="链接"></button>
            <button class="ql-image" title="图片"></button>
            <button class="ql-video" title="视频"></button>
          </div>
        </quill-editor>
      </el-card>
    </div>
    <!--提交按钮-->
    <div>
    <br>
    <el-button type="primary" round @click="Submit">确认提交</el-button>
    <el-button type="danger" round @click="Back">返回</el-button>
    </div>
  </div>
</template>

<script>
import { Quill,
  quillEditor
} from 'vue-quill-editor'
import '../../../assets/css/font.css'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
let Size = Quill.import('attributors/style/size')
Size.whitelist = ['10px', '12px', '14px', '16px', '18px', '20px']
Quill.register(Size, true)

// 自定义字体类型
var fonts = ['SimSun', 'SimHei', 'Microsoft-YaHei', 'KaiTi', 'FangSong', 'Arial', 'Times-New-Roman', 'sans-serif',
  '宋体', '黑体'
]
var Font = Quill.import('formats/font')
Font.whitelist = fonts
Quill.register(Font, true)
export default {
  name: "Wirte",
  components: {
    quillEditor
    },
  data() {
    return {
      input1: '',

      dynamicTags: [],
      multiple: true,
      inputVisible: false,
      inputValue: '',




      //el-upload
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      fileList: [],
      SelectIndex:0,
      FL:[],
      picturename:'',

      //富文本
      content: null,
      editorOption: {
        placeholder: "请输入",
        theme: "snow", // or 'bubble'
        modules: {
          toolbar: {
            container: '#toolbar'
          }
        }
      }
    };
  },
  methods: {
    Add(file){
     this.fileList.push(file.name);
      this.FL.push(document.querySelector('input[type=file]').files[0]);
      //console.log(this.fileList)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    findArrayIndex(val,vals){
      for(let i=0;i<vals.length;++i){
        if(vals[i]==val){
          return i;
        }
      }
      return 0;
    }
    ,
    //提交
    Submit() {
      if(this.FL.length===0) {
        this.$notify.warning({
          title: '警告',
          message: '请选择封面'
        });
        return;
      }else if(this.input1===""){
        this.$notify.warning({
          title: '警告',
          message: '请输入标题'
        });
        return;
      }else if(this.dynamicTags.length===0){
        this.$notify.warning({
          title: '警告',
          message: '请输入Tag'
        });
        return;
      }
      const html=this.content;
      const html_ =new Blob([html],{"type" : "text/html;charset=utf-8" })
      const formdata=new FormData();
      formdata.append('html',html_,this.input1+'.html');
      //console.log()
      //console.log(tid+"\n"+temp.name+"\n"+this.input1+"\n"+this.dynamicTags+"\n"++"\n")
      //console.log(this.SelectIndex)

      //往后端传数据
      this.addsomeHtml(this.input1,formdata)
      this.addSomePeoplePicsForm(this.SelectIndex);
      //this.addSomeText(this.input1,this.textarea2);

    },

    //添加数据库
    insertText(tid,author,path,tag,picture){
      const _this = this;
      console.log(tag+"")
      this.$axios.post(this.$store.state.baseURL+'/text/insert',
        {
          tid:tid,
          name:path,
          author:author,
          path:path+'.html',
          tag:tag+"",
          picture:picture,
          time:0,
          size:'0.01MB',
          collection:0,
          up:0,
        }
      ).then((res) => {
        console.log(res.data);
        _this.$notify.success({
          title: '成功',
          message: '成功发布!'
        });
      }).catch(
        function (error) {
          // 请求失败处理
          console.log(error)
          _this.$notify.error({
            title: '错误',
            message: '后端未启动，无法连接数据库'
          });
        }) // 发送请求
    },
    //添加图片
    addSomePeoplePicsForm: function (index) {
      const _this = this;
      let formData = new window.FormData()
      formData.append('file', this.FL[0]) // 'file' 这个名字要和后台获取文件的名字一样;
      //console.log(document.querySelector('input[type=file]').files[0])
      //'userfile'是formData这个对象的键名
      this.$axios({
        url: this.$store.state.baseURL+'/file/upload',   //****: 你的ip地址
        data: formData,
        method: 'post',
        headers: {
          'Content-Type': 'multipart/form-data',
          // 'Access-Control-Allow-Origin': 'http://127.0.0.1:8080'
          //这里是为了解决跨域问题，但是博主并没有用这种方式解决。后面会给出解决方案
        }
      }).then((res) => {
        let tid= Math.round(Math.random()*100000000);
        _this.picturename=res.data;
        _this.insertText(tid,_this.$store.state.user.name,_this.input1,_this.dynamicTags,_this.picturename);

        console.log(res.data);
      }).catch(
        function (error) {
          // 请求失败处理
          console.log(error)
          _this.$notify.error({
            title: '错误',
            message: '后端未启动，无法连接数据库'
          });
        }) // 发送请求
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    },
    Back() {
      this.$router.push({name: 'UserProfile'});
    },
    //富文本
    escapeStringHTML(str) {
      str = str.replace(/&lt;/g,'<');
      str = str.replace(/&gt;/g,'>');
      return str;
    },
    onEditorReady(editor) { // 准备编辑器

    },
    //向后端传送富文本内容
    addsomeHtml(filename,file){
      if(file==null)
      {
        console.log('null')
        return;
      }
      const _this = this;
      // 'file' 这个名字要和后台获取文件的名字一样;
      //console.log(document.querySelector('input[type=file]').files[0])
      this.$axios({
        url: this.$store.state.baseURL+'/file/uploadFile',   //****: 你的ip地址
        data: file,
        method: 'post',
        headers: {
          'Content-Type': 'multipart/form-data',
          // 'Access-Control-Allow-Origin': 'http://127.0.0.1:8080'
          //这里是为了解决跨域问题，但是博主并没有用这种方式解决。后面会给出解决方案
        }
      }).then((res) => {
        console.log(res.data);
      }).catch(
        function (error) {
          // 请求失败处理
          console.log(error)
          _this.$notify.error({
            title: '错误',
            message: '无法连接到服务器T_T'
          });
        }) // 发送请求
    },
    onEditorBlur(){}, // 失去焦点事件
    onEditorFocus(){}, // 获得焦点事件
    onEditorChange(){}, // 内容改变事件

  },
  mounted() {
    let content = '';  // 请求后台返回的内容字符串
    this.str = this.escapeStringHTML(content);
  }
}
</script>

<style scoped>
.el-select .el-input {
  width: 130px;
}

.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.el-tag + .el-tag {
  margin-left: 10px;
}

.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
