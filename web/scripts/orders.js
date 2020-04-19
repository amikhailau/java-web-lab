(function (params) {
    let formArray = [
        {
            elemType: 'number',
            label: 'Сумма заказа',
            placeholder: 'Введите сумму заказа',
            touched: false,
            correct: false,
            name: 'orderSum',
            errorEmptyMessage: 'Необходимо указать сумму',
            errorNegativeMessage: 'Сумма должна быть больше 0',
        },
		{
            elemType: 'date',
            label: 'Крайний срок доставки',
            placeholder: 'Введите срок доставки',
            errorEmptyMessage: 'Необходимо ввести срок доставки',
            errorIncorrectDate: 'Срок доставки не должен быть раньше чем сегодня',
            name: 'deliveryDeadline',
            touched: false,
            correct: false,
        },
		{
            elemType: 'checkbox',
            label: '	Оплачен ли заказ?',
            name: 'isPaid',
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
                case 'text': {
                    let node = document.createElement('div');
                    node.className = 'form-group';
                    let labelNode = document.createElement('label');
                    labelNode.textContent = item.label;
                    let inputNode = document.createElement('input');
                    inputNode.name = item.name;
                    inputNode.type = item.elemType;
                    inputNode.className = 'form-control';
                    inputNode.placeholder = item.placeholder;
                    let errorNode = document.createElement('div');
                    errorNode.className = 'invalid-feedback';
                    initTextInput(item, inputNode, errorNode);
                    node.appendChild(labelNode);
                    node.appendChild(inputNode);
                    node.appendChild(errorNode);
                    form.appendChild(node);
                    break;
                }
                case 'date': {
                    let node = document.createElement('div');
                    node.className = 'form-group';
                    let labelNode = document.createElement('label');
                    labelNode.textContent = item.label;
                    let inputNode = document.createElement('input');
                    inputNode.name = item.name;
                    inputNode.type = item.elemType;
                    inputNode.className = 'form-control';
                    inputNode.placeholder = item.placeholder;
					inputNode.min = new Date();
                    let errorEmptyNode = document.createElement('div');
                    errorEmptyNode.className = 'invalid-feedback';
					let errorInvalidNode = document.createElement('div');
                    errorInvalidNode.className = 'invalid-feedback';
                    initDateInput(item, inputNode, errorEmptyNode, errorInvalidNode);
                    node.appendChild(labelNode);
                    node.appendChild(inputNode);
                    node.appendChild(errorEmptyNode);
					node.appendChild(errorInvalidNode);
                    form.appendChild(node);
                    break;
                }
                case 'number': {
                    let node = document.createElement('div');
                    node.className = 'form-group';
                    let labelNode = document.createElement('label');
                    labelNode.textContent = item.label;
                    let inputNode = document.createElement('input');
                    inputNode.name = item.name;
                    inputNode.type = item.elemType;
                    inputNode.className = 'form-control';
                    inputNode.placeholder = item.placeholder;
                    let errorEmptyNode = document.createElement('div');
                    errorEmptyNode.className = 'invalid-feedback';
                    let errorNegativeNode = document.createElement('div');
                    errorNegativeNode.className = 'invalid-feedback';
                    initNumberInput(item, inputNode, errorEmptyNode, errorNegativeNode)
                    node.appendChild(labelNode);
                    node.appendChild(inputNode);
                    node.appendChild(errorEmptyNode);
                    node.appendChild(errorNegativeNode);
                    form.appendChild(node);
                    break;
                }
                case 'checkbox': {
                    let node = document.createElement('div');
                    node.className = 'form-group';
                    let labelNode = document.createElement('label');
                    labelNode.textContent = item.label;
                    let inputNode = document.createElement('input');
                    inputNode.name = item.name;
                    inputNode.type = item.elemType;
                    node.appendChild(inputNode);
                    node.appendChild(labelNode);
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

    function initTextInput(arrayItem, inputNode, errorNode) {
        let formSet = () => {
            arrayItem.correct = true;
            if (!inputNode.value) {
                inputNode.className = 'form-control is-invalid';
                errorNode.textContent = arrayItem.errorEmptyMessage;
            } else {
                inputNode.className = 'form-control is-valid';
                errorNode.textContent = '';
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
	
	function initDateInput(arrayItem, inputNode, errorEmptyNode, errorInvalidNode) {
        let formSet = () => {
            arrayItem.correct = true;
            if (!inputNode.value) {
                inputNode.className = 'form-control is-invalid';
                errorEmptyNode.textContent = arrayItem.errorEmptyMessage;
				arrayItem.correct = false;
            }
			if (new Date(inputNode.value) < new Date().getTime()) {
				inputNode.className = 'form-control is-invalid';
				errorInvalidNode.textContent = arrayItem.errorIncorrectDate;
				arrayItem.correct = false;
			}
			if (arrayItem.correct) {
                inputNode.className = 'form-control is-valid';
                errorEmptyNode.textContent = '';
                errorNegativeNode.textContent = '';
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

    function initNumberInput(arrayItem, inputNode, errorEmptyNode, errorNegativeNode) {
        let formSet = () => {
            arrayItem.correct = true;
            if (!inputNode.value && inputNode.value !== 0) {
                inputNode.className = 'form-control is-invalid';
                errorEmptyNode.textContent = arrayItem.errorEmptyMessage;
                arrayItem.correct = false;
            } else {
                errorEmptyNode.textContent = '';
            }
            if ((inputNode.value || inputNode.value === 0) && inputNode.value <= 0) {
                inputNode.className = 'form-control is-invalid';
                errorNegativeNode.textContent = arrayItem.errorNegativeMessage;
                arrayItem.correct = false;
            } else {
                errorNegativeNode.textContent = '';
            }
            if (arrayItem.correct) {
                inputNode.className = 'form-control is-valid';
                errorEmptyNode.textContent = '';
                errorNegativeNode.textContent = '';
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
        if (arrayItem.errorNegativeMessage) {
            let errorNegativeNode = group.getElementsByClassName('invalid-feedback')[counter];
            if ((inputNode.value || inputNode.value === 0) && inputNode.value <= 0) {
                inputNode.className = 'form-control is-invalid';
                errorNegativeNode.textContent = arrayItem.errorNegativeMessage;
                arrayItem.correct = false;
            } else {
                errorNegativeNode.textContent = '';
            }
            counter++;
        }
		if (arrayItem.errorIncorrectDate) {
			let errorNode = group.getElementsByClassName('invalid-feedback')[counter];
            if (new Date(inputNode.value) < new Date().getTime()) {
                inputNode.className = 'form-control is-invalid';
                errorNode.textContent = arrayItem.errorIncorrectDate;
            } else {
                inputNode.className = 'form-control is-valid';
                errorNode.textContent = '';
            }
            counter++;
		}
    }

    fillFormFunc('orderForm', formArray);
})();