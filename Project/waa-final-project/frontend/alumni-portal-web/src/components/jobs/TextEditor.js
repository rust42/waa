import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from "ckeditor5-classic-plus";
import defaultClient from '../../services/HttpClient';

export const TextEditor = ({ initText, onChange }) => {

    return <>
        <CKEditor
            data={initText}
            editor={ClassicEditor}
            config={{
                toolbar: {
                    items: [
                        'heading',
                        '|',
                        'bold',
                        'italic',
                        'link',
                        'bulletedList',
                        'numberedList',
                        '|',
                        'outdent',
                        'indent',
                        '|',
                        'uploadImage',
                        'blockQuote',
                        'insertTable',
                        'undo',
                        'redo',
                    ]
                },
                removePlugins: ["findAndReplace", "mediaEmbed"],
                simpleUpload: {
                    uploadUrl: defaultClient.getBaseURL() + '/files',
                    headers: {
                        Authorization: `Bearer ${defaultClient.getToken()}`
                    }
                }
            }}
            onChange={(event, editor) => {
                onChange(editor.getData())
            }}
        />
    </>
}