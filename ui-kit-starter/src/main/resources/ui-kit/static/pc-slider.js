class PcSlider extends HTMLElement {
    static get observedAttributes() {
        return ['min', 'max', 'step', 'value', 'disabled'];
    }

    constructor() {
        super();
        this.attachShadow({mode: 'open'});
    }

    connectedCallback() {
        this.render();
    }

    attributeChangedCallback() {
        if (this.shadowRoot.childElementCount) {
            this.render();
        }
    }

    render() {
        const min = this.getAttribute('min') || '0';
        const max = this.getAttribute('max') || '100';
        const step = this.getAttribute('step') || '1';
        const value = this.getAttribute('value') || min;
        const disabled = this.hasAttribute('disabled');
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: inline-flex; align-items: center; gap: .75em; min-width: 240px; }
                input { flex: 1; accent-color: var(--pc-primary, #2563eb); cursor: pointer; }
                input:disabled { opacity: .5; cursor: not-allowed; }
                output { font: inherit; min-width: 2.5em; text-align: right;
                    color: var(--pc-text, #1f2937); font-variant-numeric: tabular-nums; }
            </style>
            <input type="range" min="${min}" max="${max}" step="${step}" value="${value}" ${disabled ? 'disabled' : ''}>
            <output>${value}</output>
        `;
        const input = this.shadowRoot.querySelector('input');
        const output = this.shadowRoot.querySelector('output');
        input.addEventListener('input', () => {
            output.textContent = input.value;
            this.setAttribute('value', input.value);
        });
    }
}

customElements.define('pc-slider', PcSlider);
