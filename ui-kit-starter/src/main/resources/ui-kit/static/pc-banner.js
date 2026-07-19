class PcBanner extends HTMLElement {
    static get observedAttributes() {
        return ['variant', 'closable'];
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
        const variant = this.getAttribute('variant') || 'info';
        const closable = this.hasAttribute('closable');
        const colors = {
            info: ['#eff6ff', '#1d4ed8', '#bfdbfe'],
            success: ['#ecfdf5', '#047857', '#a7f3d0'],
            warning: ['#fffbeb', '#b45309', '#fde68a'],
            danger: ['#fef2f2', '#b91c1c', '#fecaca']
        }[variant] || ['#eff6ff', '#1d4ed8', '#bfdbfe'];
        this.shadowRoot.innerHTML = `
            <style>
                :host { display: block; }
                .banner {
                    display: flex; align-items: center; gap: .75em; padding: .75em 1em;
                    border-radius: 10px; border: 1px solid ${colors[2]};
                    background: ${colors[0]}; color: ${colors[1]}; font: inherit;
                }
                .content { flex: 1; }
                button {
                    border: none; background: transparent; color: inherit; cursor: pointer;
                    font-size: 1.2rem; line-height: 1; padding: 0 .1em; opacity: .7;
                }
                button:hover { opacity: 1; }
            </style>
            <div class="banner" role="status">
                <span class="content"><slot></slot></span>
                ${closable ? '<button type="button" aria-label="Закрыть">&times;</button>' : ''}
            </div>
        `;
        const close = this.shadowRoot.querySelector('button');
        if (close) {
            close.addEventListener('click', () => this.remove());
        }
    }
}

customElements.define('pc-banner', PcBanner);
