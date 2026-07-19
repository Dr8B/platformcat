class PcLoader extends HTMLElement {
    static get observedAttributes() {
        return ['value', 'indeterminate'];
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
        const indeterminate = this.hasAttribute('indeterminate') || !this.hasAttribute('value');
        const value = Math.max(0, Math.min(100, Number(this.getAttribute('value') || 0)));
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: block; min-width: 240px; }
                .track { height: .5em; border-radius: 999px; overflow: hidden;
                    background: var(--pc-surface, #e5e7eb); }
                .bar { height: 100%; border-radius: 999px; background: var(--pc-primary, #2563eb); }
                .bar.determinate { width: ${value}%; transition: width .3s; }
                .bar.indeterminate { width: 40%; animation: pc-slide 1.2s ease-in-out infinite; }
                @keyframes pc-slide {
                    0% { margin-left: -40%; }
                    100% { margin-left: 100%; }
                }
            </style>
            <div class="track" role="progressbar" ${indeterminate ? '' : `aria-valuenow="${value}"`}>
                <div class="bar ${indeterminate ? 'indeterminate' : 'determinate'}"></div>
            </div>
        `;
    }
}

customElements.define('pc-loader', PcLoader);
