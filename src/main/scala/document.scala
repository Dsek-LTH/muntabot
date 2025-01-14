package shared

import org.scalajs.dom
import org.scalajs.dom.document
import muntabot.Muntabot

object Document:
  def appendButton(
      targetNode: dom.Node,
      text: String,
      disabled: Boolean = false
  )(
      action: => Unit
  ): dom.html.Button =
    val button = document.createElement("button").asInstanceOf[dom.html.Button]
    button.classList.add("button")
    button.appendChild(document.createTextNode(text))
    button.onclick = (e: dom.Event) => action
    button.disabled = disabled
    targetNode.appendChild(button)
    button

  def appendText(
      targetNode: dom.Node,
      tagName: String,
      text: String
  ): dom.Element =
    val textElement = document.createElement(tagName)
    textElement.innerText = text
    targetNode.appendChild(textElement)
    textElement

  def appendLinkToApp(
      targetNode: dom.Node,
      app: App,
      textContent: String
  ) =
    val linkParagraph = document.createElement("p")
    val linkToApp =
      document.createElement("a").asInstanceOf[dom.html.Anchor]
    linkToApp.textContent = textContent
    linkToApp.href = app.link
    linkParagraph.append(linkToApp)
    targetNode.appendChild(linkParagraph)
    linkToApp

  def appendHomeLink(targetNode: dom.Node) =
    Document.appendLinkToApp(targetNode, Muntabot, "Tillbaka hem")

  def setupContainer(): dom.Element =
    val oldContainerElement = document.getElementById("container")
    if (oldContainerElement != null) then
      document.body.removeChild(oldContainerElement)

    val containerElement = document.createElement("div")
    containerElement.id = "container"
    document.body.appendChild(containerElement)
    containerElement

  def pageNotFound(): Unit =
    val containerElement = setupContainer()
    appendHomeLink(containerElement)
    appendText(containerElement, "h1", "Oops! Page not found.")
