(function (params) {
    let formArray = [
        {
            elemType: 'date',
            label: 'Крайний срок доставки',
            placeholder: 'Введите срок доставки',
            name: 'dueDate',
            errorEmptyMessage: 'Необходимо ввести срок доставки',
            touched: false,
            correct: false,
        },
        {
            elemType: 'submit',
            text: 'Подтвердить',
        },
    ];

    function fillFormFunc(formName, formArray) {
        let form = document.getElementsByName(formName)[0];
        formArray.forEach((item, index) => {
            switch (item.elemType) {
		case 'date': {
                    let node = document.createElement('div');
                    node.className = 'form-group';
                    let labelNode = document.createElement('label');
                    labelNode.textContent = item.label;
                    let inputNode = document.createElement('input');
                    inputNode.type = item.elemType;
                    inputNode.className = 'form-control';
                    inputNode.placeholder = item.placeholder;
                    inputNode.name = item.name;
                    let errorEmptyNode = document.createElement('div');
                    errorEmptyNode.className = 'invalid-feedback';
                    initDateInput(item, inputNode, errorEmptyNode);
                    node.appendChild(labelNode);
                    node.appendChild(inputNode);
                    node.appendChild(errorEmptyNode);
                    form.appendChild(node);
                    break;
                }
                case 'submit': {
                    let button = document.createElement('input');
                    button.className = 'btn btn-primary';
                    button.type = item.elemType;
                    button.textContent = item.text;
                    form.appendChild(button);
                    break;
                }
            }
        })
    }
	
    function initDateInput(arrayItem, inputNode, errorEmptyNode) {
        let formSet = () => {
            arrayItem.correct = true;
            if (!inputNode.value) {
                inputNode.className = 'form-control is-invalid';
                errorEmptyNode.textContent = arrayItem.errorEmptyMessage;
				arrayItem.correct = false;
            }
            if (arrayItem.correct) {
                inputNode.className = 'form-control is-valid';
                errorEmptyNode.textContent = '';
            }
        };
        inputNode.onblur = () => {
            arrayItem.touched = true;
            formSet();
        };
        inputNode.oninput = () => {
            if (arrayItem.touched) {
                formSet();
            }
        };
    }

    window.submitFunc = () => {
        let result = true;
        for (let index = 0; index < formArray.length; index++) {
            if (formArray[index].elemType !== 'submit'
                && formArray[index].elemType !== 'checkbox') {
                formArray[index].touched = true;
                showErrors(index, formArray[index]);
                if (!formArray[index].correct) {
                    result = false;
                }
            }
        }
        return result;
    };

    function showErrors(index, arrayItem) {
        let group = document.getElementsByClassName('form-group')[index];
        let inputNode = group.getElementsByTagName('input')[0];
        let counter = 0;
        if (arrayItem.errorEmptyMessage) {
            let errorNode = group.getElementsByClassName('invalid-feedback')[counter];
            if (!inputNode.value) {
                inputNode.className = 'form-control is-invalid';
                errorNode.textContent = arrayItem.errorEmptyMessage;
            } else {
                inputNode.className = 'form-control is-valid';
                errorNode.textContent = '';
            }
            counter++;
        }
    }

    fillFormFunc('orderDueDateForm', formArray);
})();