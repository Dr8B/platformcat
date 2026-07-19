class PcButton extends HTMLElement {
    static get observedAttributes() {
        return ['variant', 'size', 'disabled'];
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
        const variant = this.getAttribute('variant') || 'primary';
        const size = this.getAttribute('size') || 'medium';
        const disabled = this.hasAttribute('disabled');
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: inline-block; }
                button {
                    font: inherit; cursor: pointer; border: 1px solid transparent;
                    border-radius: 8px; transition: filter .15s, background .15s;
                    display: inline-flex; align-items: center; gap: .5em; line-height: 1;
                }
                button[data-size="small"]  { padding: .4em .8em;  font-size: .85rem; }
                button[data-size="medium"] { padding: .6em 1.1em; font-size: 1rem; }
                button[data-size="large"]  { padding: .8em 1.4em; font-size: 1.15rem; }
                button[data-variant="primary"]   { background: var(--pc-primary, #2563eb); color: #fff; }
                button[data-variant="secondary"] { background: var(--pc-surface, #eef2f7); color: var(--pc-text, #1f2937); border-color: #d1d9e6; }
                button[data-variant="danger"]    { background: var(--pc-danger, #dc2626); color: #fff; }
                button[data-variant="ghost"]     { background: transparent; color: var(--pc-primary, #2563eb); }
                button:not([disabled]):hover { filter: brightness(1.05); }
                button:not([disabled]):active { filter: brightness(.95); }
                button[disabled] { opacity: .5; cursor: not-allowed; }
            </style>
            <button data-variant="${variant}" data-size="${size}" ${disabled ? 'disabled' : ''}><slot>Кнопка</slot></button>
        `;
    }
}

customElements.define('pc-button', PcButton);
